package com.forum.controller;

import com.forum.domain.CustomUser;
import com.forum.dto.AuthenticationResponse;
import com.forum.dto.LoginRequest;
import com.forum.service.AuthService;
import com.forum.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;


    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid CustomUser registerRequest) {
        authService.saveUser(registerRequest);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PostMapping("/signin")
    public AuthenticationResponse signin(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
