package com.mrivals_builder.Mrivals_Builder.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;


@Component
public class JwtUtils {

    private final Algorithm ALGORITHM;
    private final Duration expiration;

    public JwtUtils(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration}") Duration expiration
    ) {
        this.ALGORITHM = Algorithm.HMAC256(secret);
        this.expiration = expiration;
    }

    public String generateToken(String email, String role) {
        return JWT.create()
                .withSubject(email)
                .withClaim("role", role)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration.toMillis()))
                .sign(ALGORITHM);
    }

    public String extractEmail(String token) {
        DecodedJWT decodedJWT = JWT.require(ALGORITHM).build().verify(token);
        return decodedJWT.getSubject();
    }

    public String extractRole(String token) {
        DecodedJWT decodedJWT = JWT.require(ALGORITHM).build().verify(token);
        return decodedJWT.getClaim("role").asString();
    }

    public boolean validateToken(String token, String username) {
        try {
            DecodedJWT decodedJWT = JWT.require(ALGORITHM).build().verify(token);
            return decodedJWT.getSubject().equals(username) && !isTokenExpired(decodedJWT);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(DecodedJWT decodedJWT) {
        return decodedJWT.getExpiresAt().before(new Date());
    }
}

