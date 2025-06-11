package com.example.commentservice.domain.comment.dto.in;

import com.example.commentservice.domain.comment.vo.in.CommentUpdateReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateReqDto {

    private String commentUuid;
    private String memberUuid;
    private String content;

    @Builder
    public CommentUpdateReqDto(
            String commentUuid,
            String memberUuid,
            String content
    ) {
        this.commentUuid = commentUuid;
        this.memberUuid = memberUuid;
        this.content = content;
    }

    public static CommentUpdateReqDto of(
            String commentUuid,
            String memberUuid,
            CommentUpdateReqVo commentUpdateReqVo
    ) {
        return CommentUpdateReqDto.builder()
                .commentUuid(commentUuid)
                .memberUuid(memberUuid)
                .content(commentUpdateReqVo.getContent())
                .build();
    }
}
