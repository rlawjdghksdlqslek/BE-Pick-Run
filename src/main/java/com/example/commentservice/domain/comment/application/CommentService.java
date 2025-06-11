package com.example.commentservice.domain.comment.application;

import com.example.commentservice.domain.comment.dto.in.CommentCreateReqDto;
import com.example.commentservice.domain.comment.dto.in.CommentDeleteReqDto;
import com.example.commentservice.domain.comment.dto.in.CommentUpdateReqDto;
import com.example.commentservice.domain.comment.dto.out.CommentListPageResDto;
import com.example.commentservice.domain.comment.entity.CommentSortType;

public interface CommentService {
    void createComment(CommentCreateReqDto commentCreateReqDto);

    void updateComment(CommentUpdateReqDto commentUpdateReqDto);

    void deleteComment(CommentDeleteReqDto commentDeleteReqDto);

    CommentListPageResDto getCommentsByPostUuid(String postUuid, int page, CommentSortType commentSortType);
}
