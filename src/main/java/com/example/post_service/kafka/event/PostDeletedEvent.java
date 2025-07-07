package com.example.post_service.kafka.event;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostDeletedEvent  {
    private final String postUuid;

    @Builder
    public PostDeletedEvent(String postUuid) {
        this.postUuid = postUuid;
    }
}
