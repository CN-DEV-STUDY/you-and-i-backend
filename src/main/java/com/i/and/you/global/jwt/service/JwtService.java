package com.i.and.you.global.jwt.service;

import com.i.and.you.global.jwt.TokenProvider;
import com.i.and.you.domain.user.entity.User;
import com.i.and.you.domain.user.service.UserService;
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

        String email = refreshTokenService.findByRefreshToken(refreshToken).getEmail();
        User user = userService.findByEmail(email);

        return tokenProvider.generateToken(user, Duration.ofHours(1));
    }
}
