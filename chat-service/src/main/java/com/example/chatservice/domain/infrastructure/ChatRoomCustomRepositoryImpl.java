package com.example.chatservice.domain.infrastructure;

import com.example.chatservice.common.response.CursorPage;
import com.example.chatservice.domain.dto.in.ChatRoomListReqDto;
import com.example.chatservice.domain.entiy.ChatRoom;
import com.example.chatservice.domain.entiy.QChatRoom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ChatRoomCustomRepositoryImpl implements ChatRoomCustomRepository {

    private final JPAQueryFactory queryFactory;

    private static final int DEFAULT_PAGE_SIZE = 10;


    public CursorPage<ChatRoom> findAllChatRoomsWithCursor(String senderUuid, ChatRoomListReqDto chatRoomListReqDto) {
        QChatRoom chatRoom = QChatRoom.chatRoom;

        int size = (chatRoomListReqDto.getSize() != null)
                ? chatRoomListReqDto.getSize()
                : DEFAULT_PAGE_SIZE;

        BooleanExpression condition = chatRoom.participantAUuid.eq(senderUuid)
                .or(chatRoom.participantBUuid.eq(senderUuid));

        if (chatRoomListReqDto.getCursor() != null) {
            // lastMessageTime 있는 경우엔 그걸 기준으로, 없으면 createdAt 기준으로
            condition = condition.and(
                    chatRoom.lastMessageTime.lt(chatRoomListReqDto.getCursor())
                            .or(chatRoom.lastMessageTime.isNull()
                                        .and(chatRoom.createdAt.lt(chatRoomListReqDto.getCursor())))
            );
        }

        List<ChatRoom> result = queryFactory
                .selectFrom(chatRoom)
                .where(condition)
                .orderBy(chatRoom.lastMessageTime.desc().nullsLast(), // 1순위 정렬
                         chatRoom.createdAt.desc()) // fallback 정렬
                .limit(size + 1)
                .fetch();

        boolean hasNext = result.size() > size;
        if (hasNext) {
            result = result.subList(0, size);
        }

        // null-safe 커서 추출 (lastMessageTime → fallback createdAt)
        String nextCursor = result.isEmpty() ? null :
                result.get(result.size() - 1).getLastMessageTime() != null
                        ? result.get(result.size() - 1).getLastMessageTime().toString()
                        : result.get(result.size() - 1).getCreatedAt().toString();

        return CursorPage.of(result, hasNext, nextCursor);
    }
}
