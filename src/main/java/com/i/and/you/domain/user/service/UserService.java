package com.i.and.you.domain.user.service;

import com.i.and.you.domain.user.dto.FindUserRequest;
import com.i.and.you.domain.user.dto.FindUserResponse;
import com.i.and.you.domain.user.dto.SaveUserRequest;
import com.i.and.you.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    Long saveUser(SaveUserRequest userDto);

    User findById(Long userId);

    User findByEmail(String email);

    void updateChatRoomId(List<String> strings, String chatRoomId);

    Page<User> findUserUsingPaging(FindUserRequest request, Pageable pageable);
}
