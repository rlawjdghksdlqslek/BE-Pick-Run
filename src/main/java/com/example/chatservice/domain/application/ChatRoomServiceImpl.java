package com.example.chatservice.domain.application;

import com.example.chatservice.domain.dto.in.CreateChatRoomReqDto;
import com.example.chatservice.domain.dto.in.MarkMessageAsReadReqDto;
import com.example.chatservice.domain.dto.out.CreateChatRoomResDto;
import com.example.chatservice.domain.entiy.ChatMessage;
import com.example.chatservice.domain.entiy.ChatRoom;
import com.example.chatservice.domain.infrastructure.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MongoTemplate mongoTemplate;

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

}
