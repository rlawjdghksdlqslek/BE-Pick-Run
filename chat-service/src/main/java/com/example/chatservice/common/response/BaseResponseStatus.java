package com.example.chatservice.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;


@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    /**
     * 200: 요청 성공
     **/
    SUCCESS(HttpStatus.OK, true, 200, "요청에 성공하였습니다."),

    // 888 : internal server error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 888, "서버에서 요청을 처리하지 못했습니다."),

    // 999 : validation error

    /**
     * 1000 ~ 1999 : member service error
     */
    // auth : 1000 ~ 1099
    NO_SIGN_IN(HttpStatus.UNAUTHORIZED, false, 1001, "로그인을 먼저 진행해주세요"),
    FAILED_TO_SIGN_UP(HttpStatus.INTERNAL_SERVER_ERROR, false, 1002, "회원가입에 실패하였습니다."),
    FAILED_TO_SIGN_IN(HttpStatus.INTERNAL_SERVER_ERROR, false, 1003, "로그인에 실패하였습니다."),

    // member : 1100 ~ 1199
    NO_EXIST_MEMBER(HttpStatus.NOT_FOUND, false, 1100, "해당 회원을 찾을 수 없습니다."),
    INVALID_GENDER_VALUE(HttpStatus.BAD_REQUEST, false, 1101, "유효하지 않은 성별 정보입니다."),
    INVALID_USER_ROLE(HttpStatus.BAD_REQUEST, false, 1102, "유효하지 않은 유저 권한입니다."),

    /**
     * 2000 : post service error
     */
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, false, 2000, "해당 게시글을 찾을 수 없습니다."),
    FAILED_TO_CREATE_POST(HttpStatus.INTERNAL_SERVER_ERROR, false, 2001, "게시글 작성에 실패하였습니다."),
    FAILED_TO_UPDATE_POST(HttpStatus.INTERNAL_SERVER_ERROR, false, 2002, "게시글 수정에 실패하였습니다."),
    FAILED_TO_DELETE_POST(HttpStatus.INTERNAL_SERVER_ERROR, false, 2003, "게시글 삭제에 실패하였습니다."),
    POST_CONTENT_TOO_LONG(HttpStatus.BAD_REQUEST, false, 2004, "게시글 내용이 너무 깁니다."),
    POST_TITLE_TOO_LONG(HttpStatus.BAD_REQUEST, false, 2005, "게시글 제목이 너무 깁니다."),
    INVALID_POST_FORMAT(HttpStatus.BAD_REQUEST, false, 2007, "게시글 형식이 잘못되었습니다."),
    POST_PERMISSION_DENIED(HttpStatus.FORBIDDEN, false, 2008, "게시글에 대한 권한이 없습니다."),

    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, false, 2100, "해당 카테고리를 찾을 수 없습니다."),
    FAILED_TO_CREATE_CATEGORY(HttpStatus.INTERNAL_SERVER_ERROR, false, 2101, "카테고리 생성에 실패하였습니다."),
    FAILED_TO_UPDATE_CATEGORY(HttpStatus.INTERNAL_SERVER_ERROR, false, 2102, "카테고리 수정에 실패하였습니다."),
    FAILED_TO_DELETE_CATEGORY(HttpStatus.INTERNAL_SERVER_ERROR, false, 2103, "카테고리 삭제에 실패하였습니다."),
    CATEGORY_NAME_ALREADY_EXISTS(HttpStatus.CONFLICT, false, 2104, "이미 존재하는 카테고리 이름입니다."),
    INVALID_CATEGORY_NAME(HttpStatus.BAD_REQUEST, false, 2105, "유효하지 않은 카테고리 이름입니다."),
    DUPLICATE_CATEGORY_LIST(HttpStatus.BAD_REQUEST, false, 2106, "중복된 카테고리 입니다.");

    /**
     * 3000 : order service error
     */

    /**
     * 4000 : chat service error
     */

    /**
     * 5000 : notice service error
     */

    private final HttpStatusCode httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private final String message;

}
