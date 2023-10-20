package com.i.and.you.auth.chat.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@RedisHash(value = "chatRoom")
public class ChatRoom {

    @Id
    private String id;
    private String chatRoomName;
    private List<Chat> chats;
}
