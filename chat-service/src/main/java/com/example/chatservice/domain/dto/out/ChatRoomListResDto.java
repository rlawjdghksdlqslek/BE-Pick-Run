package com.example.chatservice.domain.dto.out;

import com.example.chatservice.domain.entiy.ChatRoom;
import com.example.chatservice.domain.vo.out.ChatRoomListResVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatRoomListResDto {
    private String chatRoomUuid;
    private String senderUuid;
    private String receiverUuid;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private int unreadMessageCount;

    @Builder
    public ChatRoomListResDto(
            String chatRoomUuid, String senderUuid, String receiverUuid, String lastMessage,
            LocalDateTime lastMessageTime, int unreadMessageCount
    ) {
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.receiverUuid = receiverUuid;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
        this.unreadMessageCount = unreadMessageCount;
    }

    @Builder
    public ChatRoomListResDto(String chatRoomUuid, String lastMessage, LocalDateTime lastMessageTime) {
        this.chatRoomUuid = chatRoomUuid;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
    }


    public static ChatRoomListResDto from(ChatRoom chatRoom, String senderUuid) {
        String opponent = senderUuid.equals(chatRoom.getParticipantAUuid())
                ? chatRoom.getParticipantBUuid()
                : chatRoom.getParticipantAUuid();

        return ChatRoomListResDto.builder()
                .chatRoomUuid(chatRoom.getChatRoomUuid())
                .senderUuid(senderUuid)
                .receiverUuid(opponent)
                .lastMessage(chatRoom.getLastMessage())
                .lastMessageTime(chatRoom.getLastMessageTime())
                .build();
    }

}
