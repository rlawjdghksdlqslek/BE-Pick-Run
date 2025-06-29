package com.example.chatservice.domain.application;

import com.example.chatservice.domain.dto.in.SendChatMessageReqDto;
import com.example.chatservice.domain.dto.out.SendChatMessageResDto;

public interface ChatMessageService {

    SendChatMessageResDto sendMessage(SendChatMessageReqDto dto);
}
