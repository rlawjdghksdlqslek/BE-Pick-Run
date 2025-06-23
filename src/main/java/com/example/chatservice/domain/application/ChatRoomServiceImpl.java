package com.example.chatservice.domain.application;

import com.example.chatservice.domain.dto.in.CreateChatRoomReqDto;
import com.example.chatservice.domain.dto.out.CreateChatRoomResDto;
import com.example.chatservice.domain.entiy.ChatRoom;
import com.example.chatservice.domain.infrastructure.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

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


}
