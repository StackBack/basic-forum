package com.forum.service;

import com.forum.domain.Color;
import com.forum.domain.CustomUser;
import com.forum.dto.ArticleDTO;

import java.util.List;

public interface ArticleService{
    List<CustomUser> findArticleByColour(Color color);
    void saveArticle(ArticleDTO article);
    List<CustomUser> findArticleByCount();
}
