package com.i.and.you.common.jwt.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Token {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false)
    private String accessToken;


    @Column(nullable = false)
    private String refreshToken;

    //===생성 메서드===//
    public static Token createRefreshToken(Long userId, String refreshToken) {
        return Token.builder()
                .userId(userId)
                .refreshToken(refreshToken)
                .build();
    }

    public static Token createToken(Long userId, String accessToken, String refreshToken) {
        return Token.builder()
                .userId(userId)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Token update(String newRefreshToken) {
        this.refreshToken = newRefreshToken;
        return this;
    }
}
