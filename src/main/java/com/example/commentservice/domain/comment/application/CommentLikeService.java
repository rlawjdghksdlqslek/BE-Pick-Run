package com.example.commentservice.domain.comment.application;

import com.example.commentservice.domain.comment.dto.in.CommentLikeCountReqDto;
import com.example.commentservice.domain.comment.dto.in.CommentLikeReqDto;
import com.example.commentservice.domain.comment.dto.out.CommentLikeCountResDto;

public interface CommentLikeService {
    void likeComment(CommentLikeReqDto commentLikeReqDto);

    void unlikeComment(CommentLikeReqDto commentLikeReqDto);

    CommentLikeCountResDto getCommentLikeCount(CommentLikeCountReqDto commentLikeCountReqDto);
}
