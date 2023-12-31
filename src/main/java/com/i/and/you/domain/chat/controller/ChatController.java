package com.i.and.you.domain.chat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.i.and.you.domain.chat.dto.CreateChatRoomRequest;
import com.i.and.you.domain.chat.dto.ChatRoomRequest;
import com.i.and.you.domain.chat.dto.GetChatRequest;
import com.i.and.you.domain.chat.dto.GetChatResponse;
import com.i.and.you.domain.chat.entity.Chat;
import com.i.and.you.domain.chat.facade.ChatFacade;
import com.i.and.you.global.api.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatFacade chatFacade;

    @GetMapping("/chats/connection-id")
    public ResponseEntity<ApiResult<String>> createChatRoom(String email) {
        return ApiResult.createSuccess(chatFacade.getConnectionId(email));
    }


    @GetMapping("/chats")
    public ResponseEntity<ApiResult<List<GetChatResponse>>> getChats(GetChatRequest request) {
        return ApiResult.createSuccess(chatFacade.getChats(request));
    }

    @MessageMapping("/chat/{connectionId}")
    @SendTo("/queue/chat/{connectionId}")
    public Chat sendAndReceiveChat(ChatRoomRequest request) throws JsonProcessingException {
        log.info("===== chat =====");
        return chatFacade.sendAndReceiveChat(request);
    }

}
