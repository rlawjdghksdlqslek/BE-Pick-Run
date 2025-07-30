package com.example.post_service.kafka.config;

import com.example.post_service.kafka.event.PostCreatedEvent;
import com.example.post_service.kafka.event.PostDeletedEvent;
import com.example.post_service.kafka.event.PostUpdatedEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Bean
    public Map<String, Object> postProducerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, PostCreatedEvent> createPostNotification() {
        return new DefaultKafkaProducerFactory<>(postProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, PostCreatedEvent> kafkaTemplate() {
        return new KafkaTemplate<>(createPostNotification());
    }

    @Bean
    public ProducerFactory<String, PostUpdatedEvent> updatePostNotification() {
        return new DefaultKafkaProducerFactory<>(postProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, PostUpdatedEvent> postUpdateEventKafkaTemplate() {
        return new KafkaTemplate<>(updatePostNotification());
    }

    @Bean
    public ProducerFactory<String, PostDeletedEvent> deletePostNotification() {
        return new DefaultKafkaProducerFactory<>(postProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, PostDeletedEvent> postDeleteEventKafkaTemplate() {
        return new KafkaTemplate<>(deletePostNotification());
    }

}
