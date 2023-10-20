package com.i.and.you.auth.chat.repository;

import com.i.and.you.auth.chat.entity.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatRedisRepository extends CrudRepository<Chat, String> {
}
