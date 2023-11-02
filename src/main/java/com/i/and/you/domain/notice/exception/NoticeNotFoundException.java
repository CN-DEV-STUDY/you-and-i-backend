package com.i.and.you.domain.notice.exception;

import com.i.and.you.global.enums.ApiErrorCode;
import com.i.and.you.global.exception.CustomException;

public class NoticeNotFoundException extends CustomException {

    public NoticeNotFoundException() {
        super(ApiErrorCode.NOTICE_NOT_FOUND);
    }
}
