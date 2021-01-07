package com.forum.service;

import com.forum.domain.CustomUser;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<CustomUser> getUserByName(String username);
    List<CustomUser> findUserByAge(Integer age);
}
