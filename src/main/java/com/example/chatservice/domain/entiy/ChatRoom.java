package com.example.chatservice.domain.entiy;

import com.example.chatservice.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_room")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String chatRoomUuid;

    @Column(nullable = false)
    private String participantAUuid;

    @Column(nullable = false)
    private String participantBUuid;

    @Column(nullable = true, length = 255)
    private String lastMessage;

    @Column(nullable = true)
    private LocalDateTime lastMessageTime;


    @Builder
    public ChatRoom(String chatRoomUuid, String participantAUuid, String participantBUuid) {
        this.chatRoomUuid = chatRoomUuid;
        this.participantAUuid = participantAUuid;
        this.participantBUuid = participantBUuid;
    }

    public void updateLastMessage(String message, LocalDateTime time) {
        this.lastMessage = message;
        this.lastMessageTime = time;
    }
}
