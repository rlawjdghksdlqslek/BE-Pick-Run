package com.example.commentservice.domain.comment.dto.out;

import com.example.commentservice.domain.comment.vo.out.CommentLikeCountResVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentLikeCountResDto {
    private String commentUuid;
    private Long likeCount;

    @Builder
    public CommentLikeCountResDto(String commentUuid, Long likeCount) {
        this.commentUuid = commentUuid;
        this.likeCount = likeCount;
    }

    public static CommentLikeCountResVo toVo(CommentLikeCountResDto commentLikeCountResDto) {
        return CommentLikeCountResVo.builder()
                .commentUuid(commentLikeCountResDto.getCommentUuid())
                .likeCount(commentLikeCountResDto.getLikeCount())
                .build();
    }
}
