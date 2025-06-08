package com.example.postreadservice.application;

import com.example.postreadservice.dto.out.PostReadModelResDto;
import com.example.postreadservice.kafka.event.PostCreatedEvent;

public interface PostReadService {
    void createPostRead(PostCreatedEvent postCreatedEvent);

    PostReadModelResDto getPostRead(String postUuid);

}
