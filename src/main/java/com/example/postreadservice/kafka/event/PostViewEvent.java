package com.example.postreadservice.kafka.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostViewEvent {

    private String postUuid;
    private String memberUuid;
    private long timestamp;

    @Builder
    public PostViewEvent(String postUuid, String memberUuid, long timestamp) {
        this.postUuid = postUuid;
        this.memberUuid = memberUuid;
        this.timestamp = timestamp;
    }
}
