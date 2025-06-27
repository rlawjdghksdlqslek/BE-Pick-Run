package com.example.commentservice.domain.comment.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentLikeCountReqDto {
    private String commentUuid;

    @Builder
    public CommentLikeCountReqDto(String commentUuid) {
        this.commentUuid = commentUuid;
    }

    public static CommentLikeCountReqDto from(String commentUuid) {
        return CommentLikeCountReqDto.builder()
                .commentUuid(commentUuid)
                .build();
    }
}
