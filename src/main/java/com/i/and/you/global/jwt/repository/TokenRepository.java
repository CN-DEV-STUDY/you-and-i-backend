package com.i.and.you.global.jwt.repository;


import com.i.and.you.global.jwt.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByEmail(String email);
    Optional<Token> findByRefreshToken(String refreshToken);
}
