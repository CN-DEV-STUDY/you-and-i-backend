package com.i.and.you.domain.notice.exception;

import com.i.and.you.global.enums.ApiErrorCode;
import com.i.and.you.global.exception.CustomException;

public class SseException extends CustomException {
    public SseException() {
        super(ApiErrorCode.SSE_ERROR);
    }
}
