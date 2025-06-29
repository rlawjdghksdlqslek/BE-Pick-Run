package com.example.chatservice.domain.dto.in;

import com.example.chatservice.domain.entiy.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class CreateChatRoomReqDto {

    private String chatRoomUuid;
    private String participantAUuid;
    private String participantBUuid;

    @Builder
    public CreateChatRoomReqDto(String chatRoomUuid, String participantAUuid, String participantBUuid) {
        this.chatRoomUuid = chatRoomUuid;
        this.participantAUuid = participantAUuid;
        this.participantBUuid = participantBUuid;
    }

    public static CreateChatRoomReqDto of(String participantAUuid, String participantBUuid) {
        return CreateChatRoomReqDto.builder()
                .participantAUuid(participantAUuid)
                .participantBUuid(participantBUuid)
                .build();
    }

    public ChatRoom toEntity() {
        return ChatRoom.builder()
                .chatRoomUuid(UUID.randomUUID().toString())
                .participantAUuid(this.participantAUuid)
                .participantBUuid(this.participantBUuid)
                .build();
    }
}
