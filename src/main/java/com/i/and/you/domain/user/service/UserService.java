package com.i.and.you.domain.user.service;

import com.i.and.you.domain.user.dto.SaveUserRequest;
import com.i.and.you.domain.user.entity.User;

public interface UserService {

    Long saveUser(SaveUserRequest userDto);

    User findById(Long userId);

    User findByEmail(String email);
}
