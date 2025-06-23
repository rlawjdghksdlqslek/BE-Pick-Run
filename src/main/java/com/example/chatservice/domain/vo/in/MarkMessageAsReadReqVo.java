package com.example.chatservice.domain.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MarkMessageAsReadReqVo {
    private String receiverUuid;
    private String chatRoomUuid;

    @Builder
    public MarkMessageAsReadReqVo(String receiverUuid, String chatRoomUuid) {
        this.receiverUuid = receiverUuid;
        this.chatRoomUuid = chatRoomUuid;
    }
}
