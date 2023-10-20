package com.i.and.you.auth.chat.repository;

import com.i.and.you.auth.chat.entity.Chat;
import com.i.and.you.auth.chat.entity.ChatRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChatRedisRepositoryTest {

    @Autowired
    private ChatRoomRedisRepository chatRoomRedisRepository;

    @Test
    public void testRedis() throws Exception {
        // given
        Chat chat = Chat.builder()
                .chat("hello")
                .sender("sender")
                .hasRead(false)
                .createdAt(LocalDateTime.now())
                .build();

        ChatRoom myChatRoom = ChatRoom.builder()
                .chatRoomName("myChatRoom")
                .chats(Arrays.asList(chat))
                .build();

        // when
        chatRoomRedisRepository.save(myChatRoom);
        ChatRoom savedChatRoom = chatRoomRedisRepository.findById(myChatRoom.getId()).get();

        // then
        assertEquals(chat.getChat(), savedChatRoom.getChats().get(0).getChat());
    }
}