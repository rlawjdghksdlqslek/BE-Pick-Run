package com.example.chatservice.domain.infrastructure;

import com.example.chatservice.common.response.CursorPage;
import com.example.chatservice.domain.dto.in.ChatRoomListReqDto;
import com.example.chatservice.domain.entiy.ChatRoom;

public interface ChatRoomCustomRepository {
    CursorPage<ChatRoom> findAllChatRoomsWithCursor(String senderUuid, ChatRoomListReqDto chatRoomListReqDto);

}
