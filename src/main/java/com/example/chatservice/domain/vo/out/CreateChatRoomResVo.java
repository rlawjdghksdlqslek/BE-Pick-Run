package com.example.chatservice.domain.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateChatRoomResVo {
    private String ChatRoomUuid;

    @Builder
    public CreateChatRoomResVo(String chatRoomUuid) {
        ChatRoomUuid = chatRoomUuid;
    }
}
