package com.example.chatservice.domain.infrastructure;

import java.util.Map;

public interface ChatMessageCustomRepository {
    Map<String, Integer> getUnreadMessageCountByChatRoom(String receiverUuid);
}
