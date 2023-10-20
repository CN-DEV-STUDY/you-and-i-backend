package com.i.and.you.auth.chat.dto;

public record EnterChatRoomRequest(
        String chatRoomId,
        String email
) {
}
