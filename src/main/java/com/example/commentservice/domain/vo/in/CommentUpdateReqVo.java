package com.example.commentservice.domain.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateReqVo {
    private String content;

    @Builder
    public CommentUpdateReqVo(
            String content
    ) {
        this.content = content;
    }
}
