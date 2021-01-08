package com.forum.service.impl;

import com.forum.domain.Article;
import com.forum.domain.Color;
import com.forum.domain.CustomUser;
import com.forum.dto.ArticleDTO;
import com.forum.repository.ArticleRepository;
import com.forum.repository.UserRepository;
import com.forum.service.ArticleService;
import com.forum.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final AuthService authService;

    public ArticleServiceImpl(UserRepository userRepository, ArticleRepository articleRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.authService = authService;
    }

    public List<CustomUser> findArticleByColour(Color color) {
        return userRepository.findArticleByColor(color);
    }

    public boolean saveArticle(ArticleDTO article){
        Article articleSave = new Article();
        articleSave.setText(article.getText());
        articleSave.setColor(article.getColor());
        articleSave.setUser(authService.getCurrentUser());
        articleRepository.save(articleSave);
        return true;
    }

    public List<CustomUser> findArticleByCount() {
        return userRepository.findArticleByCount();
    }
}
