package com.i.and.you.user.service;

import com.i.and.you.user.dto.AddUserRequest;
import com.i.and.you.user.entity.User;

public interface UserRegisterService {
    User saveUser(AddUserRequest userDto);
}
