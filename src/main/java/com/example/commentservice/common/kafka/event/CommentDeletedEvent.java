package com.example.commentservice.common.kafka.event;

import com.example.commentservice.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDeletedEvent {

    private String memberUuid;
    private String commentUuid;
    private Boolean deleted;

    @Builder
    public CommentDeletedEvent(
            String memberUuid, String commentUuid, Boolean deleted
    ) {
        this.memberUuid = memberUuid;
        this.commentUuid = commentUuid;
        this.deleted = deleted;
    }

    public static CommentDeletedEvent from(Comment comment) {

        return CommentDeletedEvent.builder()
                .memberUuid(comment.getMemberUuid())
                .commentUuid(comment.getCommentUuid())
                .deleted(comment.isDeleted_status())
                .build();
    }
}
