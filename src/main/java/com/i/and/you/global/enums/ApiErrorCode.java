package com.i.and.you.global.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

/**
 * ApiErrorCode
*/
@Getter
public enum ApiErrorCode {

    // ========== 사용자 ========== //
    USER_NOT_FOUND("1101", HttpStatus.NOT_FOUND, "등록 되지 않은 사용자 입니다."),


    // ========== 인증 ========== //
    LOGIN_FAIL("1200", HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호가 일치하지 않습니다."),
    PASSWORD_NOT_MATCH("1201", HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    TOKEN_NOT_FOUND("1202", HttpStatus.NOT_FOUND, "토큰이 존재하지 않습니다."),
    INVALID_CHATROOM_ID("1203", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),


    // ========== notice ========== //
    SSE_ERROR("2000", HttpStatus.INTERNAL_SERVER_ERROR, "SSE 연결에 실패하였습니다."),
    NOT_MY_NOTICE("2001", HttpStatus.BAD_REQUEST, "본인의 알림만 확인할 수 있습니다."),
    NOTICE_NOT_FOUND("2002", HttpStatus.NOT_FOUND, "알림이 존재하지 않습니다.");


    private final String errorCode;

    private final HttpStatus httpStatus;

    private final String message;

    ApiErrorCode(String errorCode, HttpStatus httpStatus, String message) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public static ApiErrorCode findByErrorCode(String errorCode){
        return Arrays.stream(values())
                .filter(e -> e.getErrorCode().equals(errorCode))
                .findAny()
                .orElse(null);
    }
}
