package com.forum.service;

import com.forum.domain.CustomUser;
import com.forum.dto.AuthenticationResponse;
import com.forum.dto.LoginRequest;

public interface AuthService {
    void saveUser(CustomUser user);
    CustomUser getCurrentUser();
    AuthenticationResponse login(LoginRequest loginRequest);
}
