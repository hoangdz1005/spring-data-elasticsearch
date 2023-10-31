package com.hoangdz02.ArticleElasticSearchApplication.controller;

import com.hoangdz02.ArticleElasticSearchApplication.entity.Article;
import com.hoangdz02.ArticleElasticSearchApplication.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
    @GetMapping("/articles")
    public ResponseEntity<?> getArticles() {
        return new ResponseEntity<>(articleService.getArticles(), HttpStatus.OK);
    }

    @PostMapping("/articles")
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        return new ResponseEntity<>(articleService.addArticle(article), HttpStatus.OK);
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        return new ResponseEntity<>(articleService.updateArticle(id, article), HttpStatus.OK);
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        return new ResponseEntity<>(articleService.getArticle(id), HttpStatus.OK);
    }

    @DeleteMapping("/articles/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }
}

