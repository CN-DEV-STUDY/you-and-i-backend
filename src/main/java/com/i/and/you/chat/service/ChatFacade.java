package com.i.and.you.chat.service;

import com.i.and.you.chat.dto.CreateChatRoomRequest;
import com.i.and.you.chat.dto.EnterChatRoomRequest;
import com.i.and.you.chat.entity.Chat;
import com.i.and.you.chat.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatFacade {

    private final ChatRoomService chatRoomService;

    public String createChatRoom(CreateChatRoomRequest request) {
        ChatRoom chatRoom = chatRoomService.save(ChatRoom.createChatRoom(request));
        return chatRoom.getId();
    }

    public List<Chat> enter(EnterChatRoomRequest request) {
        ChatRoom chatRoom = chatRoomService.findById(removePrefix(request.chatRoomId()));

        if (!chatRoomService.authenticate(chatRoom, request.email())) {
            throw new IllegalArgumentException("채팅방에 참여할 수 없습니다.");
        }

        chatRoom.getChats().add(Chat.builder()
                .sender(request.email())
                .chat(request.message())
                .build());

        chatRoomService.save(chatRoom);

        return chatRoom.getChats();
    }

    private String removePrefix(String id) {
        if (!id.contains(":")) {
            return id;
        }

        return id.split(":")[1];
    }
}
