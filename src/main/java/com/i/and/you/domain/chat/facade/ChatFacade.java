package com.i.and.you.domain.chat.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.i.and.you.domain.chat.dto.CreateChatRoomRequest;
import com.i.and.you.domain.chat.dto.ChatRoomRequest;
import com.i.and.you.domain.chat.dto.GetChatRequest;
import com.i.and.you.domain.chat.dto.GetChatResponse;
import com.i.and.you.domain.chat.entity.Chat;
import com.i.and.you.domain.chat.exception.InvalidChatRoomException;
import com.i.and.you.domain.chat.service.ChatService;
import com.i.and.you.domain.user.entity.User;
import com.i.and.you.domain.user.service.UserService;
import com.i.and.you.global.enums.ApiErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatFacade {

    private final ChatService chatService;
    private final UserService userService;

    public Chat sendAndReceiveChat(ChatRoomRequest request) throws JsonProcessingException {
        User user = userService.findByEmail(request.email());

//        Chat chat = chatService.findById(removePrefix(request.chatRoomId()));

        if (!chatService.authenticate(user.getChatRoomId(), request.chatRoomId())) {
            throw new IllegalArgumentException("채팅방에 참여할 수 없습니다.");
        }

        Chat chat = Chat.createChat(request, user.getChatRoomId());

        chatService.save(chat);

        return chat;
    }

    private String removePrefix(String chatRoomId) {
        String prefix = "chat:room:";
        if (!chatRoomId.startsWith(prefix)) {
            throw new InvalidChatRoomException(ApiErrorCode.INVALID_CHATROOM_ID);
        }

        return chatRoomId.substring(prefix.length());
    }

    public List<GetChatResponse> getChats(GetChatRequest request) {
        User user = userService.findByEmail(request.email());

        if (!chatService.authenticate(user.getEmail(), request.email())) {
            throw new IllegalArgumentException("채팅방에 참여할 수 없습니다.");
        }

        List<Chat> chats = chatService.findWithPaging(user.getChatRoomId(), request.page(), request.size());

        return chats.stream()
                .map(GetChatResponse::new)
                .toList();
    }
}
