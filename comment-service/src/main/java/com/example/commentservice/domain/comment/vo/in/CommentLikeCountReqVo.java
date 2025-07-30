package com.example.commentservice.domain.comment.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentLikeCountReqVo {

    private String commentUuid;

    @Builder
    public CommentLikeCountReqVo(String commentUuid) {
        this.commentUuid = commentUuid;
    }

    public static CommentLikeCountReqVo from(String commentUuid) {
        return CommentLikeCountReqVo.builder()
                .commentUuid(commentUuid)
                .build();
    }
}
