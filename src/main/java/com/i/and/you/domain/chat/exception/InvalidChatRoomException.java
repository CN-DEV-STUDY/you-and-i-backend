package com.i.and.you.domain.chat.exception;

import com.i.and.you.global.enums.ApiErrorCode;
import com.i.and.you.global.exception.CustomException;

public class InvalidChatRoomException extends CustomException {

    public InvalidChatRoomException(ApiErrorCode apiErrorCode) {
        super(apiErrorCode);
    }

}
