package com.example.chatservice.domain.presentation;

import com.example.chatservice.domain.application.ChatMessageService;
import com.example.chatservice.domain.dto.in.SendChatMessageReqDto;
import com.example.chatservice.domain.vo.in.SendChatMessageReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(
            @Header("X-Member-UUID") String senderUuid,
            SendChatMessageReqVo vo
    ) {
        chatMessageService.sendMessage(SendChatMessageReqDto.of(senderUuid, vo));
    }

    /*@MessageMapping("/chat.read")
    public void readMessages(
            @Header("X-Member-UUID") String readerUuid,
            ReadMessageReqDto dto
    ) {
        chatMessageService.markMessagesAsRead(readerUuid, dto.getMessageIds());
    }*/
}
