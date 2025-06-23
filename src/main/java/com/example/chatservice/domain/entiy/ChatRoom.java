package com.example.chatservice.domain.entiy;

import com.example.chatservice.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    public ChatRoom(String chatRoomUuid, String participantAUuid, String participantBUuid) {
        this.chatRoomUuid = chatRoomUuid;
        this.participantAUuid = participantAUuid;
        this.participantBUuid = participantBUuid;
    }
}
