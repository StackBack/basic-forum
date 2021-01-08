package com.forum.controller;

import com.forum.domain.CustomUser;
import com.forum.service.AuthService;
import com.forum.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuthControllerTest {
    @Autowired
    private AuthController authController;
    @MockBean
    private AuthService authService;

    @Test
    void signup() {
        CustomUser customUser = new CustomUser();
        customUser.setName("testUser");
        customUser.setAge(20);
        customUser.setPassword("testPassword");

        ResponseEntity<String> signup = authController.signup(customUser);

        Assert.assertEquals(200, signup.getStatusCodeValue());
    }
}