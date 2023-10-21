package com.i.and.you.chat.repository;

import com.i.and.you.domain.chat.entity.Chat;
import com.i.and.you.domain.chat.entity.ChatRoom;
import com.i.and.you.domain.chat.repository.ChatRoomRedisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ChatRedisRepositoryTest {

    @Autowired
    private ChatRoomRedisRepository chatRoomRedisRepository;

    @Test
    public void testRedis() throws Exception {
        // given
        Chat chat = Chat.builder()
                .message("hello")
                .sender("sender")
                .hasRead(false)
                .createdAt(LocalDateTime.now())
                .build();

        ChatRoom myChatRoom = ChatRoom.builder()
                .chatRoomName("myChatRoom")
                .chats(Arrays.asList(chat))
                .participantEmails(Arrays.asList("nohyunha95@gmail.com","ddd8177@gmail.com"))
                .build();

        // when
        chatRoomRedisRepository.save(myChatRoom);
        ChatRoom savedChatRoom = chatRoomRedisRepository.findById(myChatRoom.getId()).get();

        // then
        assertEquals(chat.getMessage(), savedChatRoom.getChats().get(0).getMessage());
    }
}