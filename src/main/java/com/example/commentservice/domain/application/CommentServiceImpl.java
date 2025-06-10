package com.example.commentservice.domain.application;

import com.example.commentservice.client.post.PostServiceClient;
import com.example.commentservice.client.post.dto.out.ExistsPostResDto;
import com.example.commentservice.common.entity.BaseResponseEntity;
import com.example.commentservice.common.exception.BaseException;
import com.example.commentservice.common.response.BaseResponseStatus;
import com.example.commentservice.domain.dto.in.CommentCreateReqDto;
import com.example.commentservice.domain.infrastructure.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostServiceClient postServiceClient;

    @Transactional
    @Override
    public void createComment(CommentCreateReqDto commentCreateReqDto) {
        validatePostExists(commentCreateReqDto);
        commentRepository.save(commentCreateReqDto.toEntity());
    }

    private void validatePostExists(CommentCreateReqDto commentCreateReqDto) {
        BaseResponseEntity<ExistsPostResDto> response = postServiceClient.existsPost(commentCreateReqDto.getPostUuid());
        if (!response.result().isExistsPost()) {
            throw new BaseException(BaseResponseStatus.POST_NOT_FOUND);
        }
    }
}
