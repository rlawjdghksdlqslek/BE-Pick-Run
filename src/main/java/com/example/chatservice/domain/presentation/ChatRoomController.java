package com.example.chatservice.domain.presentation;

import com.example.chatservice.common.entity.BaseResponseEntity;
import com.example.chatservice.domain.application.ChatRoomService;
import com.example.chatservice.domain.dto.in.CreateChatRoomReqDto;
import com.example.chatservice.domain.dto.in.MarkMessageAsReadReqDto;
import com.example.chatservice.domain.vo.in.CreateChatRoomReqVo;
import com.example.chatservice.domain.vo.out.CreateChatRoomResVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat-room")
@Tag(name = "ChatRoom", description = "채팅방 관련 API")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    //채팅방 생성
    @PostMapping("/create")
    public BaseResponseEntity<CreateChatRoomResVo> createChatRoom(
            @RequestHeader("X-Member-UUID") String participantAUuid,
            @RequestBody CreateChatRoomReqVo vo
    ) {

        CreateChatRoomReqDto dto = CreateChatRoomReqDto.of(participantAUuid, vo.getParticipantBUuid());
        return new BaseResponseEntity<>(chatRoomService.createOrGetRoom(dto).toVo());
    }

    //채팅방 읽음 처리
    @PatchMapping("/read/{chatRoomUuid}")
    public BaseResponseEntity<Void> readChatMessage(
            @RequestHeader("X-Member-UUID") String receiverUuid,
            @PathVariable String chatRoomUuid
    ) {
        chatRoomService.markUnreadMessagesAsRead(MarkMessageAsReadReqDto.of(receiverUuid, chatRoomUuid));
        return new BaseResponseEntity<>();
    }

}
