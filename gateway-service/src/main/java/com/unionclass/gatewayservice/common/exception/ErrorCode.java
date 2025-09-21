package com.unionclass.gatewayservice.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * 600 ~ 699 : gateway service error
     */
    NO_JWT_TOKEN(HttpStatus.UNAUTHORIZED, false, 600, "JWT 토큰이 존재하지 않습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, false, 601, "유효하지 않은 JWT 토큰입니다."),

    /**
     * 700 ~ 799 : discovery service error
     */

    /**
     * 800 ~ 899 : internal server error
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 888, "서버에서 요청을 처리하지 못했습니다."),

    /**
     * 900 ~ 999 : validation error
     */

    /**
     * 1000 ~ 1999 : member service error
     */
    // auth : 1000 ~ 1099


    // member : 1100 ~ 1199

    /**
     * 2000 : post service error
     */

    /**
     * 3000 : order service error
     */

    ;

    private final HttpStatus httpStatus;
    private final boolean isSuccess;
    private final int code;
    private final String message;
}
