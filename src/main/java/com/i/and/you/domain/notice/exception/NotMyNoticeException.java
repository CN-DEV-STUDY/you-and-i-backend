package com.i.and.you.domain.notice.exception;

import com.i.and.you.global.enums.ApiErrorCode;
import com.i.and.you.global.exception.CustomException;

public class NotMyNoticeException extends CustomException {

    public NotMyNoticeException() {
        super(ApiErrorCode.NOT_MY_NOTICE);
    }
}
