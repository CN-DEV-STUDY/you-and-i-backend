package com.i.and.you.auth.chat.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@RedisHash(value = "chat")
@AllArgsConstructor
@Builder
public class Chat {

    @Id
    private String id;
    private String chat;
    private String sender;

    @Accessors(fluent = true)
    private boolean hasRead;
    private LocalDateTime createdAt;



}
