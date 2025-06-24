package com.example.chatservice.domain.dto.out;

import com.example.chatservice.domain.vo.out.ChatListResVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatListResDto {
    private String chatRoomUuid;
    private String opponentUuid;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private int unreadMessageCount;

    @Builder
    public ChatListResDto(
            String chatRoomUuid, String opponentUuid, String lastMessage, LocalDateTime lastMessageTime,
            int unreadMessageCount
    ) {
        this.chatRoomUuid = chatRoomUuid;
        this.opponentUuid = opponentUuid;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
        this.unreadMessageCount = unreadMessageCount;
    }

    public ChatListResVo toVo() {
        return ChatListResVo.builder()
                .chatRoomUuid(chatRoomUuid)
                .opponentUuid(opponentUuid)
                .lastMessage(lastMessage)
                .lastMessageTime(lastMessageTime)
                .unreadMessageCount(unreadMessageCount)
                .build();
    }
}
