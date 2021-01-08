package com.forum.service.impl;

import com.forum.domain.CustomUser;
import com.forum.repository.UserRepository;
import com.forum.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void getUserByName() {
        CustomUser customUser = new CustomUser();
        customUser.setName("Test");
        customUser.setAge(22);
        userRepository.save(customUser);
        Optional<CustomUser> test = userService.getUserByName("Test");
        Assert.assertEquals(customUser, test.get());
    }

    @Test
    void findUserByAge() {
    }
}