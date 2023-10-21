package com.i.and.you.domain.chat.dto;

import com.i.and.you.domain.chat.entity.Chat;
import lombok.Builder;

@Builder
public record GetChatResponse(
        String chatId,
        String message,
        String sender,
        boolean hasRead,
        String createdAt
) {

    public GetChatResponse(String chatId, String message, String sender, boolean hasRead, String createdAt) {
        this.chatId = chatId;
        this.message = message;
        this.sender = sender;
        this.hasRead = hasRead;
        this.createdAt = createdAt;
    }

    public GetChatResponse(Chat chat) {
        this(chat.getId(), chat.getMessage(), chat.getSender(), chat.hasRead(), chat.getCreatedAt().toString());
    }
}
