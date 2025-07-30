package com.example.postreadservice.kafka.consumer;

import com.example.postreadservice.application.PostReadService;
import com.example.postreadservice.kafka.event.PostDeletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class PostDeletedEventConsumer {
    private final PostReadService postReadService;

    @KafkaListener(
            topics = "post-delete-send-read",
            groupId = "post-delete-read-group",
            containerFactory = "postDeletedEventListener"
    )
    public void consumePostDeletedEvent(PostDeletedEvent postDeletedEvent) {
        log.info("📩 Received PostUpdateEvent: {}", postDeletedEvent);
        log.info("get topic {}", postDeletedEvent);
        postReadService.softDeletePostRead(postDeletedEvent);
    }
}
