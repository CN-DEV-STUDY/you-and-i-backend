package com.i.and.you.domain.chat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.i.and.you.domain.chat.entity.Chat;

import java.util.List;

public interface ChatService {

    boolean authenticate(String realChatRoomId, String requestChatRoomId);

    String save(Chat chat) throws JsonProcessingException;

    List<Chat> findWithPaging(String chatRoomId, int page, int size);

    String generateChatRoomId();
}
