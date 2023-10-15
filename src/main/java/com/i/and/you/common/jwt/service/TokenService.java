package com.i.and.you.common.jwt.service;

import com.i.and.you.common.enums.ApiErrorCode;
import com.i.and.you.common.jwt.TokenProvider;
import com.i.and.you.common.jwt.entity.Token;
import com.i.and.you.common.jwt.repository.TokenRepository;
import com.i.and.you.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenRepository tokenRepository;
    private final TokenProvider tokenProvider;

    public Token findByRefreshToken(String refreshToken) {
        return tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Refresh Token이 유효하지 않습니다.")); // TODO : exception 변경 및 spring message 사용
    }

    public Token validUserId(Long userId) {
        return tokenRepository.findByUserId(userId).get();
    }

    public void deleteById(Long tokenId) {
        tokenRepository.findById(tokenId)
                .orElseThrow(() -> new EntityNotFoundException(ApiErrorCode.TOKEN_NOT_FOUND.getMessage()));

        tokenRepository.deleteById(tokenId);
    }

    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    public String createToken(User user, Duration expiredAt) {
        return tokenProvider.generateToken(user, expiredAt);
    }
}
