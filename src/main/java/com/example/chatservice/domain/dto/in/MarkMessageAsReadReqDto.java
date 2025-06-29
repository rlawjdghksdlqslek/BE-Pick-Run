package com.example.chatservice.domain.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MarkMessageAsReadReqDto {

    private String receiverUuid;
    private String chatRoomUuid;

    @Builder
    public MarkMessageAsReadReqDto(String receiverUuid, String chatRoomUuid) {
        this.receiverUuid = receiverUuid;
        this.chatRoomUuid = chatRoomUuid;
    }

    public static MarkMessageAsReadReqDto of(String receiverUuid, String chatRoomUuid) {
        return MarkMessageAsReadReqDto.builder()
                .receiverUuid(receiverUuid)
                .chatRoomUuid(chatRoomUuid)
                .build();
    }
}
