package com.i.and.you.common.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

/**
 * ApiErrorCode
 * ERROR_CODE 넘버링 : Partner(1)
*/
@Getter
public enum ApiErrorCode {

    // ========== 파트너 ========== //
    PARTNER_NOT_FOUND("1101", HttpStatus.NOT_FOUND, "등록 되지 않은 파트너 입니다."),


    // ========== 인증 ========== //
    PASSWORD_NOT_MATCH("1201", HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    TOKEN_NOT_FOUND("1202", HttpStatus.NOT_FOUND, "토큰이 존재하지 않습니다.");


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
