package com.example.postreadservice.kafka.producer;

import com.example.postreadservice.kafka.event.PostViewEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.support.SendResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostViewKafkaProducer {

    private final KafkaTemplate<String, PostViewEvent> postViewKafkaTemplate;

    public void sendPostViewEvent(PostViewEvent postViewEvent) {
        log.info("Sending Post View Event: {}", postViewEvent);
        CompletableFuture<SendResult<String, PostViewEvent>> future = postViewKafkaTemplate.send(
                "post-view-topic", postViewEvent);
    }
}
