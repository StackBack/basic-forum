package com.forum.service;

import com.forum.dto.LoginRequest;

public interface TokenService {
    String createToken(LoginRequest user);
    Long getUserIdFromToken(String token);
    boolean isTokenValid(String token);
    Long getJwtExpirationInMills();
}
