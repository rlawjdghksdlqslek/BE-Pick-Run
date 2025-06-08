package com.example.community_service.kafka.producer;

import com.example.community_service.kafka.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostKafkaProducer {

    private final KafkaTemplate<String, PostCreatedEvent> postKafkaTemplate;

    public void sendPostEvent(PostCreatedEvent postCreatedEvent) {
        log.info("Sending PostEvent: {}", postCreatedEvent);
        CompletableFuture<SendResult<String, PostCreatedEvent>> future = postKafkaTemplate.send("post-create-send-read", postCreatedEvent);
    }

}
