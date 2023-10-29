package com.i.and.you.domain.chat.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.i.and.you.domain.chat.entity.Chat;
import com.i.and.you.domain.chat.repository.ChatRedisRepository;
import com.i.and.you.domain.chat.service.ChatService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRedisRepository chatRedisRepository;

    @Override
    public boolean authenticate(String realChatRoomId, String requestChatRoomId) {
        if (StringUtils.isBlank(realChatRoomId)) {
            throw new RuntimeException("채팅방을 먼저 등록해주세요.");
        }

        return Objects.equals(realChatRoomId, requestChatRoomId);
    }

    @Override
    public String save(Chat chat) throws JsonProcessingException {
        chatRedisRepository.save(chat);
        return chat.getId();
    }

    @Override
    public List<Chat> findWithPaging(String chatRoomId, int page, int size) {
        return chatRedisRepository.findWithPaging(chatRoomId, page, size);
    }

    @Override
    public String generateChatRoomId() {
        return "chat:room:" + UUID.randomUUID();
    }

    @Override
    public void createRoom(String chatRoomId) {
        chatRedisRepository.createRoom(chatRoomId);
    }
}
