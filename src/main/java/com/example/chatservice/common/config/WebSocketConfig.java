package com.example.chatservice.common.config;

import com.example.chatservice.common.config.interceptor.WebSocketHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // 1. 메시지 브로커 설정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트가 메시지를 구독할 prefix
        registry.enableSimpleBroker("/topic", "/queue");

        // 클라이언트가 메시지를 보낼 때 사용할 prefix
        registry.setApplicationDestinationPrefixes("/app");

        // 1:1 메시징을 위한 prefix
        registry.setUserDestinationPrefix("/user");
    }

    // 2. STOMP endpoint 등록
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 연결할 WebSocket endpoint
        registry.addEndpoint("/ws-chat")
                .setAllowedOrigins("*")
                .setAllowedOriginPatterns("*") // CORS 허용
                .addInterceptors(new WebSocketHandshakeInterceptor());
    }
}
