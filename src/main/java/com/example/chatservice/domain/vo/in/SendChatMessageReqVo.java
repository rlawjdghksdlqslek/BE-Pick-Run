package com.example.chatservice.domain.vo.in;

import lombok.Builder;
import lombok.Getter;


@Getter
public class SendChatMessageReqVo {

    private String chatRoomUuid;
    private String receiverUuid;
    private String content;

    @Builder
    public SendChatMessageReqVo(String chatRoomUuid, String receiverUuid, String content) {
        this.chatRoomUuid = chatRoomUuid;
        this.receiverUuid = receiverUuid;
        this.content = content;
    }
}
