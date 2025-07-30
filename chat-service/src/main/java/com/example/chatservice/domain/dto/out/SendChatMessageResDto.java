package com.example.chatservice.domain.dto.out;

import com.example.chatservice.domain.entiy.ChatMessage;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SendChatMessageResDto {

    private String messageUuid;
    private String chatRoomUuid;
    private String senderUuid;
    private String receiverUuid;

    private String content;
    private LocalDateTime sentAt;

    private boolean read;

    @Builder
    public SendChatMessageResDto(
            String messageUuid, String chatRoomUuid, String senderUuid, String receiverUuid, String content,
            LocalDateTime sentAt, boolean read
    ) {
        this.messageUuid = messageUuid;
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.receiverUuid = receiverUuid;
        this.content = content;
        this.sentAt = sentAt;
        this.read = read;
    }

    public static SendChatMessageResDto from(ChatMessage chatMessage) {
        return SendChatMessageResDto.builder()
                .messageUuid(chatMessage.getMessageUuId())
                .chatRoomUuid(chatMessage.getChatRoomUuid())
                .senderUuid(chatMessage.getSenderUuid())
                .receiverUuid(chatMessage.getReceiverUuid())
                .content(chatMessage.getContent())
                .sentAt(chatMessage.getSentAt())
                .read(chatMessage.isRead())
                .build();

    }
}
