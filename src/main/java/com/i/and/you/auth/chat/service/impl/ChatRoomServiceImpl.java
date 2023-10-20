package com.i.and.you.auth.chat.service.impl;

import com.i.and.you.auth.chat.dto.CreateChatRoomRequest;
import com.i.and.you.auth.chat.entity.ChatRoom;
import com.i.and.you.auth.chat.repository.ChatRoomRedisRepository;
import com.i.and.you.auth.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRedisRepository chatRoomRedisRepository;

    @Override
    public ChatRoom findById(String roomId) {
        return chatRoomRedisRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다."));
    }

    @Override
    public ChatRoom save(CreateChatRoomRequest request) {
        return chatRoomRedisRepository.save(ChatRoom.createChatRoom(request));
    }

    @Override
    public boolean authenticate(ChatRoom chatRoom, String email) {
        return chatRoom.getChats().contains(email);
    }
}
