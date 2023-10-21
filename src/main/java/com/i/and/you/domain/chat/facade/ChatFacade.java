package com.i.and.you.domain.chat.facade;

import com.i.and.you.domain.chat.dto.CreateChatRoomRequest;
import com.i.and.you.domain.chat.dto.EnterChatRoomRequest;
import com.i.and.you.domain.chat.dto.GetChatResponse;
import com.i.and.you.domain.chat.entity.Chat;
import com.i.and.you.domain.chat.entity.ChatRoom;
import com.i.and.you.domain.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatFacade {

    private final ChatRoomService chatRoomService;

    public String createChatRoom(CreateChatRoomRequest request) {
        ChatRoom chatRoom = chatRoomService.save(ChatRoom.createChatRoom(request));
        return chatRoom.getId();
    }

    public Chat enter(EnterChatRoomRequest request) {
        ChatRoom chatRoom = chatRoomService.findById(removePrefix(request.chatRoomId()));

        if (!chatRoomService.authenticate(chatRoom, request.email())) {
            throw new IllegalArgumentException("채팅방에 참여할 수 없습니다.");
        }

        Chat chat = Chat.builder()
                .sender(request.email())
                .message(request.message())
                .createdAt(LocalDateTime.now())
                .build();
        chatRoom.getChats().add(chat);

        chatRoomService.save(chatRoom);

        return chat;
    }

    private String removePrefix(String id) {
        if (!id.contains(":")) {
            return id;
        }

        return id.split(":")[1];
    }

    public List<GetChatResponse> getInitialChats(String chatRoomId, String email) {
        ChatRoom chatRoom = chatRoomService.findById(removePrefix(chatRoomId));

        if (!chatRoomService.authenticate(chatRoom, email)) {
            throw new IllegalArgumentException("채팅방에 참여할 수 없습니다.");
        }

        return chatRoom.getChats().stream()
                .map(GetChatResponse::new)
                .toList();
    }
}
