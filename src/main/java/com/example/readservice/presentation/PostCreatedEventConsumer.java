package com.example.readservice.presentation;

import com.example.readservice.application.PostReadService;
import com.example.readservice.dto.in.PostReadModelReqDto;
import com.example.readservice.kafka.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Slf4j
@Component
public class PostCreatedEventConsumer {

    private final PostReadService postReadService;

    @KafkaListener(
            topics = "post-create-send-read",
            groupId = "post-created-read-group",
            containerFactory = "postCreatedEventListener"
    )
    public void consumePostReadEvent(PostCreatedEvent postCreatedEvent) {
        log.info("📩 Received PostCreatedEvent: {}", postCreatedEvent);
        log.info("get topic {}", postCreatedEvent);
        postReadService.createPostRead(postCreatedEvent);
    }
//    step 1. create-read-post event consumer
//    step 2. get-read-join-user event consumer
//    step 3. 1, 2, getSuccess, createPostRead
}
