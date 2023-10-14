package com.i.and.you.common.jwt.repository;


import com.i.and.you.common.jwt.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByPartnerId(Long partnerId);
    Optional<Token> findByRefreshToken(String refreshToken);
}
