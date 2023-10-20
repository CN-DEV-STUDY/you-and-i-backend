package com.i.and.you.auth.chat.entity;

import com.i.and.you.auth.chat.dto.CreateChatRoomRequest;
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
    private List<String> participantEmails;
    private List<Chat> chats;

    public static ChatRoom createChatRoom(CreateChatRoomRequest request) {
        return ChatRoom.builder()
                .chatRoomName(request.chatRoomName())
                .participantEmails(request.participantEmails())
                .build();
    }
}
