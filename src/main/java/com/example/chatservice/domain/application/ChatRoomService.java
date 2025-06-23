package com.example.chatservice.domain.application;

import com.example.chatservice.domain.dto.in.CreateChatRoomReqDto;
import com.example.chatservice.domain.dto.in.MarkMessageAsReadReqDto;
import com.example.chatservice.domain.dto.out.CreateChatRoomResDto;

public interface ChatRoomService {

    CreateChatRoomResDto createOrGetRoom(CreateChatRoomReqDto dto);

    void markUnreadMessagesAsRead(MarkMessageAsReadReqDto dto);
}
