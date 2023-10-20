package com.i.and.you.auth.chat.repository;

import com.i.and.you.auth.chat.entity.Chat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChatRedisRepositoryTest {

    @Autowired
    private ChatRedisRepository chatRedisRepository;

    @Test
    public void testRedis() throws Exception {
        // given
        Chat chat = Chat.builder()
                .chat("hello")
                .sender("sender")
                .hasRead(false)
                .createdAt(LocalDateTime.now())
                .build();

        // when
        chatRedisRepository.save(chat);
        Chat savedChat = chatRedisRepository.findById(chat.getId()).get();

        // then
        assertEquals(chat.getChat(), savedChat.getChat());
    }
}