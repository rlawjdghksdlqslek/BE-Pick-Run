package com.example.postreadservice.application;

import com.example.postreadservice.common.response.BaseResponseStatus;
import com.example.postreadservice.common.exception.BaseException;
import com.example.postreadservice.dto.in.PostReadModelReqDto;
import com.example.postreadservice.dto.out.PostReadModelResDto;
import com.example.postreadservice.entity.PostReadModel;
import com.example.postreadservice.infrastructure.PostReadRepository;
import com.example.postreadservice.kafka.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PostReadServiceImpl implements PostReadService {

    private final PostReadRepository postReadRepository;

    @Transactional
    @Override
    public void createPostRead(PostCreatedEvent postCreatedEvent) {
        postReadRepository.save(PostReadModelReqDto.from(postCreatedEvent));
    }

    @Override
    public PostReadModelResDto getPostRead(String postUuid) {
        PostReadModel postReadModel = postReadRepository.findByPostUuid(postUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.POST_NOT_FOUND));
        return PostReadModelResDto.from(postReadModel);
    }


}
