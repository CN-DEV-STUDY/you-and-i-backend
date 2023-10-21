package com.i.and.you.chat.dto;

import java.util.List;

public record CreateChatRoomRequest(
        String chatRoomName,
        List<String> participantEmails
) {
}
