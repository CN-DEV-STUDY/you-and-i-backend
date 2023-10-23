package com.i.and.you.domain.chat.controller;

import com.i.and.you.domain.chat.dto.CreateChatRoomRequest;
import com.i.and.you.domain.chat.dto.EnterChatRoomRequest;
import com.i.and.you.domain.chat.dto.GetChatResponse;
import com.i.and.you.domain.chat.entity.Chat;
import com.i.and.you.domain.chat.facade.ChatFacade;
import com.i.and.you.global.api.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatFacade chatFacade;

    @PostMapping
    public ResponseEntity<ApiResult<String>> createChatRoom(CreateChatRoomRequest request) {
        return ApiResult.createSuccess(chatFacade.createChatRoom(request));
    }

    @GetMapping("/chats")
    public ResponseEntity<ApiResult<List<GetChatResponse>>> chats(String chatRoomId, String email) {
//    public ResponseEntity<ApiResult<Void>> chats(String chatRoomId, String email) {
//        return ApiResult.createSuccessWithNoContent();
        return ApiResult.createSuccess(chatFacade.getInitialChats(chatRoomId, email));
    }

    @MessageMapping("/chat")
    @SendTo("/topic/enter")
    public Chat enter(EnterChatRoomRequest request) {
        log.info("===== chat room =====");
        log.info("{} entered the chat room({}).", request.email(), request.chatRoomId());
        log.info("===== chat room =====");

        return chatFacade.enter(request);
    }

}
