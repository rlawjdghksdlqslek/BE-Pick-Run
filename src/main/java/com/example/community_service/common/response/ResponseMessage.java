package com.example.community_service.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {

    // 게시글 관련 성공 메시지
    CREATE_POST_SUCCESS("게시글 작성에 성공하였습니다."),
    UPDATE_POST_SUCCESS("게시글 수정에 성공하였습니다."),
    DELETE_POST_SUCCESS("게시글 삭제에 성공하였습니다.");

    private final String message;

}
