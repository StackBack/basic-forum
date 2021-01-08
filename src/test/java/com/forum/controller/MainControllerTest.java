package com.forum.controller;

import com.forum.domain.Color;
import com.forum.dto.ArticleDTO;
import com.forum.service.ArticleService;
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
class MainControllerTest {
    @Autowired
    private MainController mainController;
    @MockBean
    private ArticleService articleService;
    @Test
    void saveArticle() {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setColor(Color.BLACK);
        articleDTO.setText("Test TEXT");

        ResponseEntity<String> stringResponseEntity = mainController.saveArticle(articleDTO);

        Assert.assertEquals(200, stringResponseEntity.getStatusCodeValue());
    }
}