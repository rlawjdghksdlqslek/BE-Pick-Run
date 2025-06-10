package com.example.commentservice.domain.application;

import com.example.commentservice.domain.dto.in.CommentCreateReqDto;

public interface CommentService {
    void createComment(CommentCreateReqDto commentCreateReqDto);
}
