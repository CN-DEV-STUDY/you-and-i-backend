package com.i.and.you.auth.service;

import com.i.and.you.auth.dto.LoginRequest;
import com.i.and.you.auth.dto.LoginResponse;
import com.i.and.you.auth.dto.LogoutRequest;
import com.i.and.you.common.jwt.service.TokenService;
import com.i.and.you.user.entity.User;
import com.i.and.you.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthFacade {

    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UserService userService;

    @Transactional
    public LoginResponse login(LoginRequest request) {
        User user = userService.findByEmail(request.email());

        if (!isPasswordMatches(request, user)) {
            throw new RuntimeException("Password does not match");
        }

        return new LoginResponse(tokenService.createTokens(user));
    }

    private boolean isPasswordMatches(LoginRequest request, User user) {
        return passwordEncoder.matches(request.password(), user.getPassword());
    }

    @Transactional
    public void logout(LogoutRequest request) {
        tokenService.deleteByEmail(request.email());
    }
}