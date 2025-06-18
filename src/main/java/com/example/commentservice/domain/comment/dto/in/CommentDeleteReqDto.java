package com.example.commentservice.domain.comment.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDeleteReqDto {

    private String commentUuid;
    private String memberUuid;

    @Builder
    public CommentDeleteReqDto(String commentUuid, String memberUuid) {
        this.commentUuid = commentUuid;
        this.memberUuid = memberUuid;
    }

    public static CommentDeleteReqDto of(String commentUuid, String memberUuid) {
        return CommentDeleteReqDto.builder()
                .commentUuid(commentUuid)
                .memberUuid(memberUuid)
                .build();
    }

}
