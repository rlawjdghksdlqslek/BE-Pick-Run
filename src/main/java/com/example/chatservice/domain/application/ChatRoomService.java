package com.example.chatservice.domain.application;

import com.example.chatservice.domain.dto.in.CreateChatRoomReqDto;
import com.example.chatservice.domain.dto.in.MarkMessageAsReadReqDto;
import com.example.chatservice.domain.dto.out.CreateChatRoomResDto;
import com.example.chatservice.domain.dto.out.ChatListResDto;

import java.util.List;

public interface ChatRoomService {

    CreateChatRoomResDto createOrGetRoom(CreateChatRoomReqDto dto);

    void markUnreadMessagesAsRead(MarkMessageAsReadReqDto dto);

    List<ChatListResDto> getChatList(String memberUuid);
}
