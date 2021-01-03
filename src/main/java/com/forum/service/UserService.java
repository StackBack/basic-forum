package com.forum.service;

import com.forum.domain.CustomUser;
import com.forum.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<CustomUser> findUser(Integer age) {
        return userRepository.findUser(age);
    }
}
