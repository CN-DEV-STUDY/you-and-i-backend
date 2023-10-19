package com.i.and.you.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;
    private static final String TOKEN_PREFIX = "Bearer ";

    public String generateToken(com.i.and.you.user.entity.User user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    /**
     * JWT 토큰 생성 메서드
     */
    private String makeToken(Date expiry, com.i.and.you.user.entity.User user) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더 type : JWT
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)                             // 내용 iat : 현재 시간
                .setExpiration(expiry)                        // 내용 exp : expiry
                .setSubject(user.getEmail())                  // 내용 sub : 파트너 이메일
                .claim("email", user.getEmail())        // 클레임 email : user email
                // 서명 : 시크릿키값과 함께 해시값을 HS256 방식으로 암호화
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey()) // 시크릿 키 값으로 복호화
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) { // 복호화 과정에서 에러가 나면 유효하지 않은 토큰
            return false;
        }
    }

    /**
     * 토큰 기반으로 인증 정보를 가져오는 메서드
     */
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("USER"));

        return new UsernamePasswordAuthenticationToken(new User(claims.getSubject(), "", authorities), "", authorities);
    }

    private Claims getClaims(String token) {
        return Jwts.parser() // 클레임 조회
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 토큰 기반으로 유저 EMAIL을 가져오는 메서드
     */
    public String getUserEmail(String token) {
        Claims claims = getClaims(token);
        return claims.get("email", String.class);
    }

    public String getAccessToken(String authorizationHeader) {
        if (authorizationHeader != null || authorizationHeader.startsWith(TOKEN_PREFIX)) {
            // 접두사 제거
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        } else {
            return null;
        }
    }
}
