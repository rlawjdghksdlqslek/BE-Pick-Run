package com.example.readservice.application;

import com.example.readservice.dto.in.PostReadModelReqDto;
import com.example.readservice.dto.out.PostReadModelResDto;
import com.example.readservice.entity.PostReadModel;
import com.example.readservice.infrastructure.PostReadRepository;
import com.example.readservice.kafka.event.PostCreatedEvent;
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
    public void createPostRead(PostCreatedEvent postCreatedEvent) {
        postReadRepository.save(PostReadModelReqDto.from(postCreatedEvent));
    }

    @Override
    public PostReadModelResDto getPostRead(String postUuid) {
        PostReadModel postReadModel = postReadRepository.findByPostUuid(postUuid);
        return PostReadModelResDto.from(postReadModel);
    }


}
