package com.example.chatservice.domain.vo.in;

import lombok.Builder;
import lombok.Getter;


@Getter
public class SendChatMessageReqVo {

    private String chatRoomUuid;
    private String senderUuid;
    private String receiverUuid;
    private String content;

    @Builder
    public SendChatMessageReqVo(String chatRoomUuid, String senderUuid, String receiverUuid, String content) {
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.receiverUuid = receiverUuid;
        this.content = content;
    }
}
