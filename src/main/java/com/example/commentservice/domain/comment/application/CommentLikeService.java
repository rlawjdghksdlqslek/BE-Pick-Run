package com.example.commentservice.domain.comment.application;

import com.example.commentservice.domain.comment.dto.in.CommentLikeReqDto;

public interface CommentLikeService {
    void likeComment(CommentLikeReqDto commentLikeReqDto);
}
