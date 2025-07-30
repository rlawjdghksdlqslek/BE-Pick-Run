package com.skill.bookmarkservice.common.entity;

import com.skill.bookmarkservice.common.response.BaseResponseStatus;
import org.springframework.http.HttpStatus;

public record BaseResponseEntity<T>(HttpStatus httpStatus, Boolean isSuccess, String message, int code, T result) {

    /**
     * 필요값 : Http상태코드, 성공여부, 메시지, 에러코드, 결과값
     * 1. Return 객체가 필요한 경우 -> 성공
     * 2. Return 객체가 필요 없는 경우 -> 성공
     * 3. 요청에 실패한 경우
     */

    /**
     * 1. Return 객체가 필요한 경우 -> 성공
     * @param result
     */
    public BaseResponseEntity(T result) {
        this(HttpStatus.OK, true, "SUCCESS", 200, result);
    }

    /**
     * 2. Return 객체가 필요 없는 경우 -> 성공
     */
    public BaseResponseEntity() {
        this(HttpStatus.OK, true, "SUCCESS", 200, null);
    }

    /**
     * 3. 요청에 실패한 경우
     * @param status
     */
    public BaseResponseEntity(BaseResponseStatus status) {
        this(status.getHttpStatus(), status.isSuccess(), status.getMessage(), status.getCode(), null);
    }

    public BaseResponseEntity(BaseResponseStatus status, String message) {
        this(status.getHttpStatus(), status.isSuccess(), message, status.getCode(), null);
    }
}

