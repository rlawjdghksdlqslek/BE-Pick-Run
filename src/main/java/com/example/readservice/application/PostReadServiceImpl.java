package com.example.readservice.application;

import com.example.readservice.dto.in.PostReadModelReqDto;
import com.example.readservice.infrastructure.PostReadRepository;
import com.example.readservice.kafka.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostReadServiceImpl implements PostReadService {

    private final PostReadRepository postReadRepository;

    public void createPostRead(PostCreatedEvent postCreatedEvent) {
        postReadRepository.save(PostReadModelReqDto.from(postCreatedEvent));
    }


}
