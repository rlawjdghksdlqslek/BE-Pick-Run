package com.example.chatservice.domain.dto.out;

import com.example.chatservice.domain.vo.out.CreateChatRoomResVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateChatRoomResDto {
    private String chatRoomUuid;

    @Builder
    public CreateChatRoomResDto(String chatRoomUuid) {
        this.chatRoomUuid = chatRoomUuid;
    }

    public CreateChatRoomResVo toVo() {
        return CreateChatRoomResVo.builder()
                .chatRoomUuid(chatRoomUuid)
                .build();
    }
}
