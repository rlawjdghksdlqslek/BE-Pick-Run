package com.example.chatservice.domain.presentation;

import com.example.chatservice.common.entity.BaseResponseEntity;
import com.example.chatservice.common.response.CursorPage;
import com.example.chatservice.domain.application.ChatRoomService;
import com.example.chatservice.domain.dto.in.ChatMessageReqDto;
import com.example.chatservice.domain.dto.in.CreateChatRoomReqDto;
import com.example.chatservice.domain.dto.in.MarkMessageAsReadReqDto;
import com.example.chatservice.domain.dto.out.ChatListResDto;
import com.example.chatservice.domain.dto.out.ChatMessageResDto;
import com.example.chatservice.domain.vo.in.CreateChatRoomReqVo;
import com.example.chatservice.domain.vo.out.ChatListResVo;
import com.example.chatservice.domain.vo.out.CreateChatRoomResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat-room")
@Tag(name = "ChatRoom", description = "채팅방 관련 API")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @Operation(
            summary = "채팅방 생성",
            description = """
                        두 사용자의 UUID를 기반으로 채팅방을 생성하거나 기존 채팅방을 반환합니다.
                    
                        [요청 경로]
                        - POST /api/v1/chat-room/create
                    
                        [요청 헤더]
                        - X-Member-UUID: (String) 채팅 참여자 A의 UUID
                    
                        [요청 바디]
                        - participantBUuid: (String) 채팅 참여자 B의 UUID
                    
                        [응답 필드]
                        - chatRoomUuid: (String) 생성된 채팅방 UUID
                    
                        [처리 로직]
                        - 두 참여자 간 기존 채팅방 존재 여부 확인 후 없으면 새로 생성
                    
                        [예외 상황]
                        - INVALID_MEMBER_UUID: 잘못된 사용자 UUID 형식
                    """
    )
    @PostMapping("/create")
    public BaseResponseEntity<CreateChatRoomResVo> createChatRoom(
            @RequestHeader("X-Member-UUID") String participantAUuid,
            @RequestBody CreateChatRoomReqVo vo
    ) {

        CreateChatRoomReqDto dto = CreateChatRoomReqDto.of(participantAUuid, vo.getParticipantBUuid());
        return new BaseResponseEntity<>(chatRoomService.createOrGetRoom(dto).toVo());
    }

    @Operation(
            summary = "채팅방 읽음 처리",
            description = """
                        사용자가 채팅방의 안읽은 메시지를 모두 읽음 처리합니다.
                    
                        [요청 경로]
                        - PATCH /api/v1/chat-room/read/{chatRoomUuid}
                    
                        [요청 헤더]
                        - X-Member-UUID: (String) 읽은 사람의 UUID
                    
                        [요청 파라미터]
                        - path variable: chatRoomUuid (String) 채팅방 고유 ID
                    
                        [처리 로직]
                        - 해당 채팅방의 안 읽은 메시지를 읽음 처리
                    
                    """
    )
    @PatchMapping("/read/{chatRoomUuid}")
    public BaseResponseEntity<Void> readChatMessage(
            @RequestHeader("X-Member-UUID") String receiverUuid,
            @PathVariable String chatRoomUuid
    ) {
        chatRoomService.markUnreadMessagesAsRead(MarkMessageAsReadReqDto.of(receiverUuid, chatRoomUuid));
        return new BaseResponseEntity<>();
    }

    @Operation(
            summary = "채팅방 목록 조회",
            description = """
                        회원 UUID 기준으로 해당 사용자의 채팅방 목록을 조회합니다.
                    
                        [요청 경로]
                        - GET /api/v1/chat-room/list
                    
                        [요청 헤더]
                        - X-Member-UUID: (String) 회원 고유 식별자
                    
                        [응답 필드]
                        - chatRoomUuid, lastMessage, unreadCount 등 포함된 채팅방 리스트
                    
                        [처리 로직]
                        - 해당 회원이 속한 모든 채팅방 목록 조회
                    
                        [예외 상황]
                        - INVALID_MEMBER_UUID: 잘못된 UUID 입력
                    """
    )
    @GetMapping("/list")
    public BaseResponseEntity<List<ChatListResVo>> getChatRoomList(
            @RequestHeader("X-Member-UUID") String memberUuid
    ) {
        return new BaseResponseEntity<>(chatRoomService.getChatList(memberUuid)
                                                .stream()
                                                .map(ChatListResDto::toVo)
                                                .toList());
    }

    @GetMapping("/{chatRoomUuid}/message")
    public BaseResponseEntity<CursorPage<ChatMessageResDto>> getChatMessages(
            @PathVariable String chatRoomUuid,
            @ModelAttribute ChatMessageReqDto chatMessageReqDto
    ) {
        return new BaseResponseEntity<>(chatRoomService.getChatMessages(chatRoomUuid, chatMessageReqDto));
    }
}
