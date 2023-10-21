package com.i.and.you.domain.chat.service.impl;

import com.i.and.you.domain.chat.entity.ChatRoom;
import com.i.and.you.domain.chat.repository.ChatRoomRedisRepository;
import com.i.and.you.domain.chat.service.ChatRoomService;
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
    public ChatRoom save(ChatRoom chatRoom) {
        return chatRoomRedisRepository.save(chatRoom);
    }

    @Override
    public boolean authenticate(ChatRoom chatRoom, String email) {
        return chatRoom.getParticipantEmails().contains(email);
    }
}
