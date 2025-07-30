package com.example.postreadservice.kafka.consumer;

import com.example.postreadservice.application.PostReadService;
import com.example.postreadservice.kafka.event.PostUpdatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class PostUpdatedEventConsumer {
    private final PostReadService postReadService;

    @KafkaListener(
            topics = "post-update-send-read",
            groupId = "post-update-read-group",
            containerFactory = "postUpdatedEventListener"
    )
    public void consumePostUpdatedEvent(PostUpdatedEvent postUpdatedEvent) {
        log.info("📩 Received PostUpdateEvent: {}", postUpdatedEvent);
        log.info("get topic {}", postUpdatedEvent);
        postReadService.updatePostRead(postUpdatedEvent);
    }
}
