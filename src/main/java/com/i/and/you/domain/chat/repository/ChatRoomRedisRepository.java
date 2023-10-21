package com.i.and.you.domain.chat.repository;

import com.i.and.you.domain.chat.entity.ChatRoom;
import org.springframework.data.repository.CrudRepository;

public interface ChatRoomRedisRepository extends CrudRepository<ChatRoom, String> {
}
