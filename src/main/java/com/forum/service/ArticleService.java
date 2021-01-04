package com.forum.service;

import com.forum.domain.Article;
import com.forum.domain.Color;
import com.forum.domain.CustomUser;
import com.forum.repository.ArticleRepository;
import com.forum.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final UserRepository userRepository;

    public ArticleService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<CustomUser> findArticleByColour(Color color) {
        return userRepository.findArticleByColor(color);
    }

    public List<CustomUser> findArticleByCount() {
        return userRepository.findArticleByCount();
    }
}
