package com.i.and.you.auth.chat.repository;

import com.i.and.you.auth.chat.entity.ChatRoom;
import org.springframework.data.repository.CrudRepository;

public interface ChatRoomRedisRepository extends CrudRepository<ChatRoom, String> {
}
