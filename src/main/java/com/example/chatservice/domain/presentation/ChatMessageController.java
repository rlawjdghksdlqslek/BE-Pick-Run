package com.example.chatservice.domain.presentation;

import com.example.chatservice.domain.application.ChatMessageService;
import com.example.chatservice.domain.dto.in.SendChatMessageReqDto;
import com.example.chatservice.domain.dto.out.SendChatMessageResDto;
import com.example.chatservice.domain.vo.in.SendChatMessageReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(
            @Header("X-Member-UUID") String senderUuid,
            SendChatMessageReqVo vo
    ) {
        SendChatMessageResDto dto = chatMessageService.sendMessage(
                SendChatMessageReqDto.of(senderUuid, vo));

        // 2. 메시지 대상자에게 전송 (/user/{receiverUuid}/queue/messages)
        simpMessagingTemplate.convertAndSend("/queue/messages" + dto.getChatRoomUuid(), dto);

    }
}
