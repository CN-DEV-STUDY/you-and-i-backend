package com.i.and.you.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class Chat {

    private String id;
    private String chat;
    private String sender;

    @Accessors(fluent = true)
    private boolean hasRead;
    private LocalDateTime createdAt;
}
