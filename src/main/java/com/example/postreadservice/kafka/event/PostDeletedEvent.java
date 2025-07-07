package com.example.postreadservice.kafka.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDeletedEvent {
    private String postUuid;

    @Builder
    public PostDeletedEvent(String postUuid) {
        this.postUuid = postUuid;
    }
}