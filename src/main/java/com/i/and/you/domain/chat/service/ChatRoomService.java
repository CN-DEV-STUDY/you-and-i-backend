package com.i.and.you.domain.chat.service;

import com.i.and.you.domain.chat.dto.GetChatResponse;
import com.i.and.you.domain.chat.entity.ChatRoom;

import java.util.List;

public interface ChatRoomService {
    ChatRoom findById(String s);

    boolean authenticate(ChatRoom chatRoom, String email);

    ChatRoom save(ChatRoom chatRoom);

}
