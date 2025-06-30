package com.example.chatservice.domain.dto.out;

import com.example.chatservice.domain.entiy.ChatMessage;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatMessageResDto {
    private String messageUuid;
    private String senderUuid;
    private String content;
    private LocalDateTime sentAt;
    private boolean read;

    @Builder
    public ChatMessageResDto(
            String messageUuid, String senderUuid, String content, LocalDateTime sentAt, boolean read) {
        this.messageUuid = messageUuid;
        this.senderUuid = senderUuid;
        this.content = content;
        this.sentAt = sentAt;
        this.read = read;
    }

    public static ChatMessageResDto from(ChatMessage chatMessage) {
        return ChatMessageResDto.builder()
                .messageUuid(chatMessage.getMessageUuId())
                .senderUuid(chatMessage.getSenderUuid())
                .content(chatMessage.getContent())
                .sentAt(chatMessage.getSentAt())
                .read(chatMessage.isRead())
                .build();
    }
}
