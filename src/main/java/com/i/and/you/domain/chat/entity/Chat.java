package com.i.and.you.domain.chat.entity;

import com.i.and.you.domain.chat.dto.ChatRoomRequest;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@ToString
@NoArgsConstructor
public class Chat {

    private String id;
    private String message;
    private String sender;
    @Accessors(fluent = true)
    private boolean hasRead;
    private LocalDateTime createdAt;


    //===생성 메서스===//
    public static Chat createChat(ChatRoomRequest request) {
        return Chat.builder()
                .id(generateChatId())
                .message(request.message())
                .sender(request.email())
                .createdAt(LocalDateTime.now())
                .build();
    }

    //===비즈니스 로직===//
    private static String generateChatId() {
        return "chat:room:" + UUID.randomUUID();
    }
}
