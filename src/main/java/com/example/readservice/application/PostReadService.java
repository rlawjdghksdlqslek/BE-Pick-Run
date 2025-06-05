package com.example.readservice.application;

import com.example.readservice.dto.out.PostReadModelResDto;
import com.example.readservice.kafka.event.PostCreatedEvent;

public interface PostReadService {
    void createPostRead(PostCreatedEvent postCreatedEvent);

    PostReadModelResDto getPostRead(String postUuid);

}
