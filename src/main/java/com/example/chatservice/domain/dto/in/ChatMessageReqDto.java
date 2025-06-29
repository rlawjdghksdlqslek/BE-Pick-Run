package com.example.chatservice.domain.dto.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ChatMessageReqDto {

    private String senderUuid;
    private LocalDateTime cursor;
    private Integer size;

}
