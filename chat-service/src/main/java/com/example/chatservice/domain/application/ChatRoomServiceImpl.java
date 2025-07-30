package com.example.chatservice.domain.application;

import com.example.chatservice.common.response.CursorPage;
import com.example.chatservice.domain.dto.in.ChatMessageReqDto;
import com.example.chatservice.domain.dto.in.ChatRoomListReqDto;
import com.example.chatservice.domain.dto.in.CreateChatRoomReqDto;
import com.example.chatservice.domain.dto.in.MarkMessageAsReadReqDto;
import com.example.chatservice.domain.dto.out.ChatMessageResDto;
import com.example.chatservice.domain.dto.out.ChatRoomListResDto;
import com.example.chatservice.domain.dto.out.CreateChatRoomResDto;
import com.example.chatservice.domain.entiy.ChatMessage;
import com.example.chatservice.domain.entiy.ChatRoom;
import com.example.chatservice.domain.infrastructure.ChatMessageRepository;
import com.example.chatservice.domain.infrastructure.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final MongoTemplate mongoTemplate;

    @Transactional
    @Override
    public CreateChatRoomResDto createOrGetRoom(CreateChatRoomReqDto dto) {
        Optional<ChatRoom> existingRoom = chatRoomRepository.findByParticipants(
                dto.getParticipantAUuid(),
                dto.getParticipantBUuid()
        );

        if (existingRoom.isPresent()) {
            ChatRoom room = existingRoom.get();
            return CreateChatRoomResDto.builder()
                    .chatRoomUuid(room.getChatRoomUuid())
                    .build();
        }

        ChatRoom newRoom = dto.toEntity();
        chatRoomRepository.save(newRoom);

        return CreateChatRoomResDto.builder()
                .chatRoomUuid(newRoom.getChatRoomUuid())
                .build();
    }

    @Override
    public void markUnreadMessagesAsRead(MarkMessageAsReadReqDto dto) {
        Query query = new Query(
                Criteria.where("chatRoomUuid").is(dto.getChatRoomUuid())
                        .and("receiverUuid").is(dto.getReceiverUuid())
                        .and("read").is(false)
        );

        Update update = new Update().set("read", true);
        mongoTemplate.updateMulti(query, update, ChatMessage.class);
    }

    @Override
    public CursorPage<ChatRoomListResDto> getChatRoomList(String senderUuid, ChatRoomListReqDto chatRoomListReqDto) {
        CursorPage<ChatRoom> chatRooms = chatRoomRepository.findAllChatRoomsWithCursor(senderUuid, chatRoomListReqDto);
        return chatRooms.map(chatRoom -> ChatRoomListResDto.from(chatRoom, senderUuid));
    }

    @Override
    public ChatRoomListResDto getChatRoom(String memberUuid, String chatRoomUuid) {
        ChatRoom chatRoom = chatRoomRepository.findByChatRoomUuid(chatRoomUuid)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다."));

        return ChatRoomListResDto.from(chatRoom,memberUuid);
    }

    @Override
    public CursorPage<ChatMessageResDto> getChatMessages(String chatRoomUuid, ChatMessageReqDto chatMessageReqDto) {
        CursorPage<ChatMessage> page = chatMessageRepository.findChatMessagesByCursor(chatRoomUuid, chatMessageReqDto);

        List<ChatMessageResDto> dtoList = page.getContent().stream()
                .map(ChatMessageResDto::from)
                .toList();

        return CursorPage.of(dtoList, page.getHasNext(), page.getNextCursor());
    }
}
