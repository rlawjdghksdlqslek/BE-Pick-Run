package com.example.postreadservice.kafka.consumer;

import com.example.postreadservice.kafka.event.PostViewEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class PostViewEventConsumer {

    private final RedisTemplate<String, Object> redisTemplate;

    @KafkaListener(
            topics = "post-view-topic",
            groupId = "post-view-consumer-group",
            containerFactory = "postViewEventListener"
    )
    public void consumePostViewEvent(PostViewEvent postViewEvent) {
        String redisKey = "aggregate:post:view:" + postViewEvent.getPostUuid();

        log.info("redisKey: {}, value: {}", redisKey, redisTemplate.opsForValue().get(redisKey));
        redisTemplate.opsForValue().increment(redisKey);
        log.info("Value 증가 redisKey: {}, value: {}", redisKey, redisTemplate.opsForValue().get(redisKey));
    }
}
