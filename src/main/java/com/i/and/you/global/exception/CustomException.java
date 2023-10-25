package com.i.and.you.global.exception;

import com.i.and.you.global.enums.ApiErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private ApiErrorCode apiErrorCode;

    public CustomException() {
    }

    public CustomException(ApiErrorCode apiErrorCode) {
        this.apiErrorCode = apiErrorCode;
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

}
