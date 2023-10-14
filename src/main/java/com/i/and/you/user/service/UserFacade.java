package com.i.and.you.user.service;

import com.i.and.you.common.jwt.TokenProvider;
import com.i.and.you.user.dto.SaveUserRequest;
import com.i.and.you.user.dto.SaveUserResponse;
import com.i.and.you.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class UserFacade {

    private final UserService userService;
    private final TokenProvider tokenProvider;

    public SaveUserResponse save(SaveUserRequest request) {
        Long userId = userService.saveUser(request);

        return new SaveUserResponse(
                tokenProvider.generateToken(userService.findById(userId), Duration.ofMinutes(30))
        );
    }
}
