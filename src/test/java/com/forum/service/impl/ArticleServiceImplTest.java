package com.forum.service.impl;

import com.forum.domain.Article;
import com.forum.domain.Color;
import com.forum.dto.ArticleDTO;
import com.forum.repository.ArticleRepository;
import com.forum.service.ArticleService;
import com.forum.service.AuthService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.config.http.MatcherType.mvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceImplTest {
    @Autowired
    private ArticleService articleService;
    @MockBean
    private AuthService authService;
    @MockBean
    private ArticleRepository articleRepository;

    @Test
    public void saveArticle() {
        ArticleDTO article = new ArticleDTO();
        article.setColor(Color.RED);
        article.setText("Test Text");

        boolean isArticleCreated = articleService.saveArticle(article);

        Assert.assertTrue(isArticleCreated);
    }
}