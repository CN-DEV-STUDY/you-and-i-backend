package com.i.and.you.domain.user.facade;

import com.i.and.you.domain.user.service.UserService;
import com.i.and.you.global.jwt.TokenProvider;
import com.i.and.you.domain.user.dto.SaveUserRequest;
import com.i.and.you.domain.user.dto.SaveUserResponse;
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
