package com.i.and.you.auth.chat.service;

import com.i.and.you.auth.chat.dto.CreateChatRoomRequest;
import com.i.and.you.auth.chat.entity.ChatRoom;

public interface ChatRoomService {
    ChatRoom findById(String s);

    boolean authenticate(ChatRoom chatRoom, String email);

    ChatRoom save(CreateChatRoomRequest request);
}
