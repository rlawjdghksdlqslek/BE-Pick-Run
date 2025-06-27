package com.example.commentservice.domain.comment.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentLikeCountResVo {
    private String commentUuid;
    private Long likeCount;

    @Builder
    public CommentLikeCountResVo(String commentUuid, Long likeCount) {
        this.commentUuid = commentUuid;
        this.likeCount = likeCount;
    }
}
