package com.i.and.you.auth.chat.service;

import com.i.and.you.auth.chat.dto.CreateChatRoomRequest;
import com.i.and.you.auth.chat.dto.EnterChatRoomRequest;
import com.i.and.you.auth.chat.entity.Chat;
import com.i.and.you.auth.chat.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatFacade {

    private final ChatRoomService chatRoomService;

    public String createChatRoom(CreateChatRoomRequest request) {
        ChatRoom chatRoom = chatRoomService.save(request);
        return chatRoom.getId();
    }

    public List<Chat> enter(EnterChatRoomRequest request) {
        ChatRoom chatRoom = chatRoomService.findById(request.chatRoomId());

        boolean authenticated = chatRoomService.authenticate(chatRoom, request.email());

        if (!authenticated) {
            throw new IllegalArgumentException("채팅방에 참여할 수 없습니다.");
        }

        return chatRoom.getChats();
    }
}
