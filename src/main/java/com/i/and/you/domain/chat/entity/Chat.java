package com.i.and.you.domain.chat.entity;

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
    private String message;
    private String sender;

    @Accessors(fluent = true)
    private boolean hasRead;
    private LocalDateTime createdAt;
}
