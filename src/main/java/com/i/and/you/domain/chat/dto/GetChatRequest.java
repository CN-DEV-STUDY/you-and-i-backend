package com.i.and.you.domain.chat.dto;

public record GetChatRequest(
        String email,
        int page,
        int size
) {
}
