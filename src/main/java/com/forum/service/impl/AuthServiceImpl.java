package com.forum.service.impl;

import com.forum.domain.CustomUser;
import com.forum.dto.AuthenticationResponse;
import com.forum.dto.LoginRequest;
import com.forum.exception.AuthException;
import com.forum.repository.UserRepository;
import com.forum.security.JWTFilter;
import com.forum.service.AuthService;
import com.forum.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,
                           AuthenticationManager authenticationManager,
                           TokenService tokenService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Transactional
    public boolean saveUser(CustomUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public AuthenticationResponse login(LoginRequest loginRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenService.createToken(loginRequest);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .expiresAt(Instant.now().plusMillis(tokenService.getJwtExpirationInMills()))
                .username(loginRequest.getName())
                .build();
    }

    public CustomUser getCurrentUser(){
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByName(principal.getUsername())
                .orElseThrow(() -> new AuthException("User name not found - " + principal.getUsername()));
    }
}
