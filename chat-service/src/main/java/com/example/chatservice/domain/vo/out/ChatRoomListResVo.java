package com.example.chatservice.domain.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatRoomListResVo {
    private String chatRoomUuid;
    private String receiverUuid;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private int unreadMessageCount;

    @Builder
    public ChatRoomListResVo(
            String chatRoomUuid, String receiverUuid, String lastMessage, LocalDateTime lastMessageTime,
            int unreadMessageCount
    ) {
        this.chatRoomUuid = chatRoomUuid;
        this.receiverUuid = receiverUuid;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
        this.unreadMessageCount = unreadMessageCount;
    }
}
