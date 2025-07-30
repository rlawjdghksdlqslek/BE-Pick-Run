package com.example.commentservice.common.kafka.event;

import com.example.commentservice.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentCreatedEvent {

    private String memberUuid;
    private String commentUuid;
    private Boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public CommentCreatedEvent(
            String memberUuid, String commentUuid, Boolean deleted, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.memberUuid = memberUuid;
        this.commentUuid = commentUuid;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static CommentCreatedEvent from(Comment comment) {
        return CommentCreatedEvent.builder()
                .memberUuid(comment.getMemberUuid())
                .commentUuid(comment.getCommentUuid())
                .deleted(comment.isDeleted_status())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
}
