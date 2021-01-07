package com.forum.service.impl;

import com.forum.domain.CustomUser;
import com.forum.repository.UserRepository;
import com.forum.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<CustomUser> getUserByName(String username) {
        return userRepository.findByName(username);
    }


    public List<CustomUser> findUserByAge(Integer age) {
        return userRepository.findUser(age);
    }
}