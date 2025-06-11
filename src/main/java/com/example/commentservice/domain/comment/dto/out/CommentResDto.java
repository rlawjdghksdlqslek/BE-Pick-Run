package com.example.commentservice.domain.comment.dto.out;

import com.example.commentservice.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CommentResDto {

    private String commentUuid;
    private String postUuid;
    private String memberUuid;
    private String content;
    private boolean blind_status;
    private boolean deleted_status;

    @Builder
    public CommentResDto(
            String commentUuid, String postUuid, String memberUuid, String content, boolean blind_status,
            boolean deleted_status
    ) {
        this.commentUuid = commentUuid;
        this.postUuid = postUuid;
        this.memberUuid = memberUuid;
        this.content = content;
        this.blind_status = blind_status;
        this.deleted_status = deleted_status;
    }


    public static CommentResDto from(Comment comment) {
        return CommentResDto.builder()
                .commentUuid(comment.getCommentUuid())
                .postUuid(comment.getPostUuid())
                .memberUuid(comment.getMemberUuid())
                .content(comment.getContent())
                .blind_status(comment.isBlind_status())
                .deleted_status(comment.isDeleted_status())
                .build();
    }
}
