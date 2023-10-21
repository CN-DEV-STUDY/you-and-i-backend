package com.i.and.you.domain.chat.dto;

public record EnterChatRoomRequest(
        String chatRoomId,
        String email,
        String message
) {
}
