package com.example.chatservice.domain.infrastructure;

import com.example.chatservice.domain.entiy.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
}
