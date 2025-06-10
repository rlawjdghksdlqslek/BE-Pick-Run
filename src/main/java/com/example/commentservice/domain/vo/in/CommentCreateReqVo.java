package com.example.commentservice.domain.vo.in;

import lombok.Builder;
import lombok.Getter;


@Getter
public class CommentCreateReqVo {

    private String content;

    @Builder
    public CommentCreateReqVo(String content) {
        this.content = content;
    }
}
