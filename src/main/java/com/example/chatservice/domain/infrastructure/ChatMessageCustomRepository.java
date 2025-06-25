package com.example.chatservice.domain.infrastructure;

import com.example.chatservice.common.response.CursorPage;
import com.example.chatservice.domain.dto.in.ChatMessageReqDto;
import com.example.chatservice.domain.entiy.ChatMessage;

import java.util.Map;

public interface ChatMessageCustomRepository {
    Map<String, Integer> getUnreadMessageCountByChatRoom(String receiverUuid);

    CursorPage<ChatMessage> findChatMessagesByCursor(String chatRoomUuid, ChatMessageReqDto chatMessageReqDto);
}
