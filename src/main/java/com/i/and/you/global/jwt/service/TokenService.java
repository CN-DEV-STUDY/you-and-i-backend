package com.i.and.you.global.jwt.service;

import com.i.and.you.global.enums.ApiErrorCode;
import com.i.and.you.global.jwt.TokenProvider;
import com.i.and.you.global.jwt.entity.Token;
import com.i.and.you.global.jwt.repository.TokenRepository;
import com.i.and.you.domain.user.entity.User;
import com.i.and.you.global.jwt.enums.TokenDuration;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenRepository tokenRepository;
    private final TokenProvider tokenProvider;

    public Token findByRefreshToken(String refreshToken) {
        return tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Refresh Token이 유효하지 않습니다.")); // TODO : exception 변경 및 spring message 사용
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteByEmail(String email) {
        Token token = tokenRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ApiErrorCode.TOKEN_NOT_FOUND.getMessage()));

        tokenRepository.delete(token);
    }

    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    @Transactional
    public String createTokens(User user, boolean rememberMe) {
        String accessToken = "";
        String refreshToken = "";

        if (rememberMe) {
            accessToken = tokenProvider.generateToken(user, TokenDuration.REMEMBER_ME_ACCESS_TOKEN_DURATION.getDuration());
            refreshToken = tokenProvider.generateToken(user, TokenDuration.REMEMBER_ME_REFRESH_TOKEN_DURATION.getDuration());
        } else {
            accessToken = tokenProvider.generateToken(user, TokenDuration.ACCESS_TOKEN_DURATION.getDuration());
            refreshToken = tokenProvider.generateToken(user, TokenDuration.REFRESH_TOKEN_DURATION.getDuration());
        }

        tokenRepository.save(Token.createToken(user.getEmail(), accessToken, refreshToken));
        return accessToken;
    }

    public Token findByEmail(String email) {
        return tokenRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ApiErrorCode.TOKEN_NOT_FOUND.getMessage()));
    }
}
