package com.i.and.you.chat.dto;

public record EnterChatRoomRequest(
        String chatRoomId,
        String email,
        String message
) {
}
