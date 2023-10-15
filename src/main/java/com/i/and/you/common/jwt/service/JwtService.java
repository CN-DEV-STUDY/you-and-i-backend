package com.i.and.you.common.jwt.service;

import com.i.and.you.common.jwt.TokenProvider;
import com.i.and.you.user.entity.User;
import com.i.and.you.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class JwtService {
    private final TokenService refreshTokenService;
    private final TokenProvider tokenProvider;
    private final UserService userService;

    /**
     * access token은 발급 후 1시간 동안 유효하다.
     */
    public String generateNewAccessToken(String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new IllegalArgumentException("Refresh Token이 유효하지 않습니다."); // TODO : exception 변경 및 spring message 사용
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(1));
    }
}
