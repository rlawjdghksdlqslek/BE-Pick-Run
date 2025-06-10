package com.example.commentservice.domain.application;

import com.example.commentservice.domain.dto.in.CommentCreateReqDto;
import com.example.commentservice.domain.dto.in.CommentUpdateReqDto;

public interface CommentService {
    void createComment(CommentCreateReqDto commentCreateReqDto);

    void updateComment(CommentUpdateReqDto commentUpdateReqDto);
}
