package com.i.and.you.user.service;

import com.i.and.you.user.dto.SaveUserRequest;
import com.i.and.you.user.entity.User;

public interface UserService {

    Long saveUser(SaveUserRequest userDto);

    User findById(Long userId);
}
