package com.example.commentservice.common.kafka.util;

import com.example.commentservice.common.kafka.event.CommentCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("${spring.kafka.topics.comment-created}")
    private String commentCreatedTopic;

    private final KafkaTemplate<String, CommentCreatedEvent> commentCreatedEventKafkaTemplate;

    public void sendCommentCreatedEvent(CommentCreatedEvent commentCreatedEvent) {

        log.info("Kafka 메시지 전송 시작: {}", commentCreatedEvent);

        CompletableFuture<SendResult<String, CommentCreatedEvent>> future
                = commentCreatedEventKafkaTemplate.send(commentCreatedTopic , commentCreatedEvent);

        future.whenComplete((result, ex) -> {
            if(ex != null) {
                log.error("Kafka 메시지 전송 실패: {}", commentCreatedEvent, ex);
            } else {
                log.info("Kafka 메시지 전송 성공: offset={}, topic={}",
                        result.getRecordMetadata().offset(), result.getRecordMetadata().topic());
            }
        });
    }
}
