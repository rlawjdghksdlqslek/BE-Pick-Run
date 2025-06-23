package com.example.chatservice.domain.application;

import com.example.chatservice.domain.dto.in.SendChatMessageReqDto;
import com.example.chatservice.domain.dto.out.SendChatMessageResDto;
import com.example.chatservice.domain.infrastructure.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    @Override
    public SendChatMessageResDto sendMessage(SendChatMessageReqDto dto) {
        return SendChatMessageResDto.from(chatMessageRepository.save(dto.toEntity()));
    }
}
