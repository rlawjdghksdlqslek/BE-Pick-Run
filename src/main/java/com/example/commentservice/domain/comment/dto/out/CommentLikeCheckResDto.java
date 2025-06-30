package com.example.commentservice.domain.comment.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentLikeCheckResDto {

    private String commentUuid;
    private boolean liked;

    @Builder
    public CommentLikeCheckResDto(String commentUuid, boolean liked) {
        this.commentUuid = commentUuid;
        this.liked = liked;
    }
}
