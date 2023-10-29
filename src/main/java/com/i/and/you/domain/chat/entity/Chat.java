package com.i.and.you.domain.chat.entity;

import com.i.and.you.domain.chat.dto.ChatRoomRequest;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

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
    public static Chat createChat(ChatRoomRequest request, String chatRoomId) {
        return Chat.builder()
                .id(chatRoomId)
                .message(request.message())
                .sender(request.email())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
