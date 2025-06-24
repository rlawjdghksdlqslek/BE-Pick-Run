package com.example.chatservice.domain.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatListResVo {
    private String chatRoomUuid;
    private String opponentUuid;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private int unreadMessageCount;

    @Builder
    public ChatListResVo(
            String chatRoomUuid, String opponentUuid, String lastMessage, LocalDateTime lastMessageTime,
            int unreadMessageCount
    ) {
        this.chatRoomUuid = chatRoomUuid;
        this.opponentUuid = opponentUuid;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
        this.unreadMessageCount = unreadMessageCount;
    }
}
