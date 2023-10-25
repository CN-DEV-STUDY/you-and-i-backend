package com.i.and.you.domain.auth.exception;

import com.i.and.you.global.enums.ApiErrorCode;
import com.i.and.you.global.exception.CustomException;

public class LoginFailException extends CustomException {

    public LoginFailException(ApiErrorCode apiErrorCode) {
        super(apiErrorCode);
    }
}
