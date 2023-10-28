package com.i.and.you.domain.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.i.and.you.domain.chat.entity.Chat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class ChatServiceTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void redisOpsForListTest() throws Exception {
        // given
        ListOperations<String, Object> stringChatListOperations = redisTemplate.opsForList();

        String chatRoomId = UUID.randomUUID().toString();
        for (int i = 0; i < 10; i++) {
            Chat chat = Chat.builder()
                    .id("chat:room:" + chatRoomId)
                    .message("hello" + i)
                    .sender("sender" + i)
                    .hasRead(false)
                    .createdAt(LocalDateTime.now())
                    .build();

            stringChatListOperations.rightPush(chat.getId(), chat);

            List<Object> range = stringChatListOperations.range(chat.getId(), 0, -1);
            for (Object chat1 : range) {
                System.out.println("chat.getId() = " + chat.getId());
                System.out.println(chat1.toString());
            }
        }
    }

    @Test
    public void lrangeTest() throws Exception {
        // given
        List<Object> range = redisTemplate.opsForList().range("chat:room:40b455fd-9cac-4800-b12e-3a3a3c697a50", 0, -1);


        for (Object o : range) {
            Chat chat = objectMapper.convertValue(o, Chat.class);
            System.out.println("chat = " + chat);
            System.out.println("o = " + o);
        }
        // when

        // then
    }
}