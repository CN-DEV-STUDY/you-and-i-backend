package com.i.and.you.auth.chat.controller;

import com.i.and.you.auth.chat.dto.CreateChatRoomRequest;
import com.i.and.you.auth.chat.dto.EnterChatRoomRequest;
import com.i.and.you.auth.chat.entity.Chat;
import com.i.and.you.auth.chat.service.ChatFacade;
import com.i.and.you.common.api.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/chat")
@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatFacade chatFacade;

    @PostMapping
    public ResponseEntity<ApiResult<String>> createChatRoom(CreateChatRoomRequest request) {
        return ApiResult.createSuccess(chatFacade.createChatRoom(request));
    }

    @MessageMapping("/enter/{chatRoomId}")
    @SendTo("/topic/{chatRoomId}")
    public List<Chat> enter(EnterChatRoomRequest request) {
        return chatFacade.enter(request);
    }

}
