package com.example.post_service.kafka.producer;

import com.example.post_service.kafka.event.PostCreatedEvent;
import com.example.post_service.kafka.event.PostDeletedEvent;
import com.example.post_service.kafka.event.PostUpdatedEvent;
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
    private final KafkaTemplate<String, PostUpdatedEvent> postUpdateKafkaTemplate;
    private final KafkaTemplate<String, PostDeletedEvent> postDeleteKafkaTemplate;

    public void sendPostEvent(PostCreatedEvent postCreatedEvent) {
        log.info("Sending PostEvent: {}", postCreatedEvent);
        CompletableFuture<SendResult<String, PostCreatedEvent>> future = postKafkaTemplate.send(
                "post-create-send-read", postCreatedEvent);
    }

    public void sendUpdatePostEvent(PostUpdatedEvent postUpdatedEvent) {
        log.info("Sending Post Update Event: {}", postUpdatedEvent);
        CompletableFuture<SendResult<String, PostUpdatedEvent>> future = postUpdateKafkaTemplate.send(
                "post-update-send-read", postUpdatedEvent);
    }

    public void sendDeletePostEvent(PostDeletedEvent postDeletedEvent) {
        log.info("Sending Post Update Event: {}", postDeletedEvent);
        CompletableFuture<SendResult<String, PostDeletedEvent>> future = postDeleteKafkaTemplate.send(
                "post-delete-send-read", postDeletedEvent);
    }
}
