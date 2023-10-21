package com.i.and.you.domain.chat.controller;

import com.i.and.you.domain.chat.dto.CreateChatRoomRequest;
import com.i.and.you.domain.chat.dto.EnterChatRoomRequest;
import com.i.and.you.domain.chat.entity.Chat;
import com.i.and.you.domain.chat.facade.ChatFacade;
import com.i.and.you.global.api.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatFacade chatFacade;

    @PostMapping
    public ResponseEntity<ApiResult<String>> createChatRoom(CreateChatRoomRequest request) {
        return ApiResult.createSuccess(chatFacade.createChatRoom(request));
    }

    @MessageMapping("/chat")
    @SendTo("/topic/enter")
    public List<Chat> enter(EnterChatRoomRequest request) {
        log.info("===== chat room =====");
        log.info("{} entered the chat room({}).", request.email(), request.chatRoomId());
        log.info("===== chat room =====");

        return chatFacade.enter(request);
    }

}
