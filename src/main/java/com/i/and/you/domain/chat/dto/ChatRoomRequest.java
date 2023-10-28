package com.i.and.you.domain.chat.dto;

public record ChatRoomRequest(
        String chatRoomId,
        String email,
        String message
) {
}
