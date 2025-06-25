package com.example.chatservice.domain.application;

import com.example.chatservice.common.response.CursorPage;
import com.example.chatservice.domain.dto.in.ChatMessageReqDto;
import com.example.chatservice.domain.dto.in.CreateChatRoomReqDto;
import com.example.chatservice.domain.dto.in.MarkMessageAsReadReqDto;
import com.example.chatservice.domain.dto.out.ChatListResDto;
import com.example.chatservice.domain.dto.out.ChatMessageResDto;
import com.example.chatservice.domain.dto.out.CreateChatRoomResDto;

import java.util.List;

public interface ChatRoomService {

    CreateChatRoomResDto createOrGetRoom(CreateChatRoomReqDto dto);

    void markUnreadMessagesAsRead(MarkMessageAsReadReqDto dto);

    List<ChatListResDto> getChatList(String memberUuid);

    CursorPage<ChatMessageResDto> getChatMessages(String chatRoomUuid, ChatMessageReqDto dto);
}
