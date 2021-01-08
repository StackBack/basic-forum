package com.forum.service.impl;

import com.forum.domain.CustomUser;
import com.forum.repository.UserRepository;
import com.forum.service.AuthService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;
import static org.springframework.security.config.http.MatcherType.mvc;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuthServiceImplTest {
    @Autowired
    private AuthService authService;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private UserRepository userRepository;

    @Test
    void saveUser() {
        CustomUser customUser = new CustomUser();
        customUser.setName("TestText");
        customUser.setAge(22);
        customUser.setPassword("TestPassword123");

        boolean isUserCreated = authService.saveUser(customUser);

        Assert.assertTrue(isUserCreated);
    }
}