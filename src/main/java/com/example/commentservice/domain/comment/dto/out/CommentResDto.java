package com.example.commentservice.domain.comment.dto.out;

import com.example.commentservice.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class CommentResDto {

    private String commentUuid;
    private String postUuid;
    private String memberUuid;
    private String content;
    private boolean blind_status;
    private boolean deleted_status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public CommentResDto(
            String commentUuid, String postUuid, String memberUuid, String content, boolean blind_status,
            boolean deleted_status, LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.commentUuid = commentUuid;
        this.postUuid = postUuid;
        this.memberUuid = memberUuid;
        this.content = content;
        this.blind_status = blind_status;
        this.deleted_status = deleted_status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public static CommentResDto from(Comment comment) {
        return CommentResDto.builder()
                .commentUuid(comment.getCommentUuid())
                .postUuid(comment.getPostUuid())
                .memberUuid(comment.getMemberUuid())
                .content(comment.getContent())
                .blind_status(comment.isBlind_status())
                .deleted_status(comment.isDeleted_status())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
}
