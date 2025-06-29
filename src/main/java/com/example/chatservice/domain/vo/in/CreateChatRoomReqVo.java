package com.example.chatservice.domain.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateChatRoomReqVo {
    private String participantBUuid;

    @Builder
    public CreateChatRoomReqVo(String participantAUuid, String participantBUuid) {
        this.participantBUuid = participantBUuid;
    }
}
