package com.example.community_service.kafka.producer;

import com.example.community_service.kafka.event.PostEvent;
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

    private final KafkaTemplate<String, PostEvent> postKafkaTemplate;

    public void sendPostEvent(PostEvent postEvent) {
        log.info("Sending PostEvent: {}", postEvent);
        CompletableFuture<SendResult<String, PostEvent>> future = postKafkaTemplate.send("create-post-send-read", postEvent);
    }

}
