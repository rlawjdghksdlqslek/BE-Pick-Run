package com.example.commentservice.domain.comment.application;

import com.example.commentservice.common.exception.BaseException;
import com.example.commentservice.common.response.BaseResponseStatus;
import com.example.commentservice.domain.comment.dto.in.CommentLikeCountReqDto;
import com.example.commentservice.domain.comment.dto.in.CommentLikeReqDto;
import com.example.commentservice.domain.comment.dto.out.CommentLikeCountResDto;
import com.example.commentservice.domain.comment.entity.CommentLike;
import com.example.commentservice.domain.comment.infrastructure.CommentLikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;


    @Transactional
    @Override
    public void likeComment(CommentLikeReqDto commentLikeReqDto) {
        if (commentLikeRepository.existsByCommentUuidAndMemberUuid(
                commentLikeReqDto.getCommentUuid(), commentLikeReqDto.getMemberUuid())) {
            log.warn(
                    "이미 댓글에 좋아요를 하셨습니다 : commentUuid={}, memberUuid={} ",
                    commentLikeReqDto.getCommentUuid(), commentLikeReqDto.getMemberUuid()
            );
            throw new BaseException(BaseResponseStatus.ALREADY_EXISTS_COMMENT_LIKE);
        }
        commentLikeRepository.save(commentLikeReqDto.toEntity());
    }

    @Transactional
    @Override
    public void unlikeComment(CommentLikeReqDto commentLikeReqDto) {
        CommentLike commentLike = commentLikeRepository
                .findByCommentUuidAndMemberUuid(commentLikeReqDto.getCommentUuid(), commentLikeReqDto.getMemberUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.INVALID_USER_ROLE));

        commentLikeRepository.delete(commentLike);
    }

    @Override
    public CommentLikeCountResDto getCommentLikeCount(CommentLikeCountReqDto commentLikeCountReqDto) {
        long count = commentLikeRepository.countByCommentUuid(commentLikeCountReqDto.getCommentUuid());
        return CommentLikeCountResDto.builder()
                .commentUuid(commentLikeCountReqDto.getCommentUuid())
                .likeCount(count)
                .build();
    }

}
