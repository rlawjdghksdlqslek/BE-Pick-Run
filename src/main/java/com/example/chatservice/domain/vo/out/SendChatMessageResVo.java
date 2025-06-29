package com.example.chatservice.domain.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SendChatMessageResVo {

    private String chatRoomUuid;
    private String senderUuid;
    private String receiverUuid;

    private String content;
    private LocalDateTime sentAt;

    private boolean read;

    @Builder
    public SendChatMessageResVo(
            String chatRoomUuid, String senderUuid, String receiverUuid, String content, LocalDateTime sentAt,
            boolean read
    ) {
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.receiverUuid = receiverUuid;
        this.content = content;
        this.sentAt = sentAt;
        this.read = read;
    }
}
