package com.i.and.you.common.jwt.service;

import com.i.and.you.common.enums.ApiErrorCode;
import com.i.and.you.common.jwt.TokenProvider;
import com.i.and.you.common.jwt.entity.Token;
import com.i.and.you.common.jwt.repository.TokenRepository;
import com.i.and.you.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.i.and.you.common.jwt.enums.TokenDuration.ACCESS_TOKEN_DURATION;
import static com.i.and.you.common.jwt.enums.TokenDuration.REFRESH_TOKEN_DURATION;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenRepository tokenRepository;
    private final TokenProvider tokenProvider;

    public Token findByRefreshToken(String refreshToken) {
        return tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Refresh Token이 유효하지 않습니다.")); // TODO : exception 변경 및 spring message 사용
    }

    public void deleteByEmail(String email) {
        Token token = tokenRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ApiErrorCode.TOKEN_NOT_FOUND.getMessage()));

        tokenRepository.delete(token);
    }

    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    public String createTokens(User user) {
        tokenRepository.findByEmail(user.getEmail())
                .ifPresent(tokenRepository::delete);

        String accessToken = tokenProvider.generateToken(user, ACCESS_TOKEN_DURATION.getDuration());
        String refreshToken = tokenProvider.generateToken(user, REFRESH_TOKEN_DURATION.getDuration());
        tokenRepository.save(Token.createToken(user.getEmail(), accessToken, refreshToken));
        return accessToken;
    }
}
