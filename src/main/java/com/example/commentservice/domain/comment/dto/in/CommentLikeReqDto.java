package com.example.commentservice.domain.comment.dto.in;

import com.example.commentservice.domain.comment.entity.Comment;
import com.example.commentservice.domain.comment.entity.CommentLike;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentLikeReqDto {

    private String commentUuid;
    private String memberUuid;

    @Builder
    public CommentLikeReqDto(String commentUuid, String memberUuid) {
        this.commentUuid = commentUuid;
        this.memberUuid = memberUuid;
    }

    public static CommentLikeReqDto of(String commentUuid, String memberUuid) {
        return new CommentLikeReqDto(commentUuid, memberUuid);
    }

    public CommentLike toEntity() {
        return CommentLike.builder()
                .commentUuid(this.commentUuid)
                .memberUuid(this.memberUuid)
                .build();
    }
}
