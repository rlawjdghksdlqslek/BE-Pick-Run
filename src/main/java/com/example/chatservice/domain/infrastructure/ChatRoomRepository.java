package com.example.chatservice.domain.infrastructure;

import com.example.chatservice.domain.entiy.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query(
            "SELECT r FROM ChatRoom r WHERE " +
                    "(r.participantAUuid = :user1 AND r.participantBUuid = :user2) OR " +
                    "(r.participantAUuid = :user2 AND r.participantBUuid = :user1)"
    )
    Optional<ChatRoom> findByParticipants(@Param("user1") String user1, @Param("user2") String user2);

    Optional<ChatRoom> findByChatRoomUuid(String chatRoomUuid);
}
