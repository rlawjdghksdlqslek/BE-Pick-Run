package com.unionclass.gatewayservice.common.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;

@RequiredArgsConstructor
@Slf4j
@Service
public class JwtProvider {

    private final Environment env;

    /**
     * JwtProvider
     *
     * 1. 토큰 검증
     */

    /**
     * 1. 토큰 검증
     *
     * @param token jwtToken
     * @return true(유효) / false(X)
     */
    public boolean validateToken(String token) {
        try {
            log.info("JWT 토큰 검증 시작");
            Jwts
                    .parser()
                    .verifyWith((SecretKey) getSignKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            log.info("JWT 토큰 검증 성공");
            return true;
        } catch (ExpiredJwtException e) {
            log.info("JWT 토큰 검증 실패 - 토큰 만료됨");
            return false;
        } catch (Exception e) {
            log.info("JWT 토큰 검증 실패 - 예외 발생", e);
            return false;
        }
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(env.getProperty("jwt.secret-key").getBytes());
    }
}