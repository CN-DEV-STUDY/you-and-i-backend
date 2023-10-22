package com.i.and.you.domain.auth.dto;

public record LoginResponse(
        String accessToken,
        String chatRoomId,
        String email
) {
}
