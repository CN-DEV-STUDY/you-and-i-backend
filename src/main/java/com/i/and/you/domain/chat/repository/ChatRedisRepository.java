package com.i.and.you.domain.chat.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.i.and.you.domain.chat.entity.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ChatRedisRepository {

    private final RedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public void save(Chat chat) {
        redisTemplate.opsForList().leftPush(chat.getId(), chat);
    }

    public List<Chat> findWithPaging(String chatRoomId, int page, int size) {

        int start = (page - 1) * size;
        int end = start + size;

        boolean hasNext = redisTemplate.opsForList().size(chatRoomId) > end;

        List<Object> chats = redisTemplate.opsForList().range(chatRoomId, 0, -1);

        return chats.stream()
                .map(chat -> objectMapper.convertValue(chat, Chat.class))
                .sorted(Comparator.comparing(Chat::getCreatedAt))
                .toList();

    }

    public void createRoom(String chatRoomId) {
        redisTemplate.opsForList().leftPush(chatRoomId, new Chat());
    }
}
