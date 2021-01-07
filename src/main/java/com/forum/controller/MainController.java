package com.forum.controller;

import com.forum.domain.Color;
import com.forum.domain.CustomUser;
import com.forum.dto.ArticleDTO;
import com.forum.service.ArticleService;
import com.forum.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("users")
public class MainController {
    private final UserService userService;
    private final ArticleService articleService;

    public MainController(UserService userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }

    @GetMapping("/by-age")
    public ResponseEntity<List<CustomUser>> findUserByAge(Integer age){
        return status(HttpStatus.OK).body(userService.findUserByAge(age));
    }

    @GetMapping("/by-color")
    public ResponseEntity<List<CustomUser>> findUserByColor(@RequestParam("color") Color color){
        return status(HttpStatus.OK).body(articleService.findArticleByColour(color));
    }

    @GetMapping("/count")
    public ResponseEntity<List<CustomUser>> findArticleByCount(){
        return status(HttpStatus.OK).body(articleService.findArticleByCount());
    }

    @PostMapping("/art")
    public ResponseEntity<String> saveArticle(@RequestBody ArticleDTO article){
        articleService.saveArticle(article);
        return status(HttpStatus.OK).body("Article created successfully");
    }
}
