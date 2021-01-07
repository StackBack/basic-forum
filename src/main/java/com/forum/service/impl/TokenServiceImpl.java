package com.forum.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.forum.domain.CustomUser;
import com.forum.dto.LoginRequest;
import com.forum.repository.UserRepository;
import com.forum.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

import static java.util.Objects.nonNull;

@Service
public class TokenServiceImpl implements TokenService {
    private final UserRepository userRepository;
    private static final String TOKEN_SECRET = "ForceElate2018";
    private static final String USER_ID = "userId";
    private static final String CREATED_AT = "createdAt";
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMills;

    public TokenServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createToken(LoginRequest user) {
        String jwt = null;
        try {
            Algorithm algorithm = Algorithm.HMAC512(TOKEN_SECRET);
            jwt = JWT.create()
                    .withClaim(USER_ID, findUserByName(user.getName()).getId())
                    .withClaim(CREATED_AT, new Date())
                    .withExpiresAt(Date.from(Instant.now().plusMillis(jwtExpirationInMills)))
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            System.out.println("F");
        }
        return jwt;
    }

    public Long getUserIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim(USER_ID).asLong();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public CustomUser findUserByName(String name){
        return userRepository.findCustomUserByName(name);
    }

    public boolean isTokenValid(String token) {
        Long userId = this.getUserIdFromToken(token);
        return nonNull(userId);
    }

    public Long getJwtExpirationInMills() {
        return jwtExpirationInMills;
    }
}
