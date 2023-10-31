package com.hoangdz02.ArticleElasticSearchApplication.controller;

import com.hoangdz02.ArticleElasticSearchApplication.entity.Article;
import com.hoangdz02.ArticleElasticSearchApplication.search.ArticleELKService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ArticleSearchController {
    private final ArticleELKService articleELKService;
    public ArticleSearchController(ArticleELKService articleELKService) {
        this.articleELKService = articleELKService;
    }
    @GetMapping("/match")
    public ResponseEntity<List<Article>> matchWithTitle(@RequestParam(name = "keyword") String keyword) {
        return new ResponseEntity<>(articleELKService.matchQueryWithTitle(keyword), HttpStatus.OK);
    }
    @GetMapping("/multi-match")
    public ResponseEntity<List<Article>> multiMatchWithTitleAndTag(@RequestParam String keyword) {
        return new ResponseEntity<>(articleELKService.multiMatchQueryWithTitleAndTag(keyword), HttpStatus.OK);
    }
    @GetMapping("/term")
    public ResponseEntity<List<Article>> termWithTitle(@RequestParam(name = "keyword") String keyword) {
        return new ResponseEntity<>(articleELKService.termQueryWithTitle(keyword), HttpStatus.OK);
    }
    @GetMapping("/bool")
    public ResponseEntity<List<Article>> boolWithTitle(@RequestParam(name = "title") String title, @RequestParam(name = "tag") String tag, @RequestParam(name = "isPublished") Boolean isPublished) {
        return new ResponseEntity<>(articleELKService.boolQueryWithTitleAndTagAndPublished(title, tag, isPublished), HttpStatus.OK);
    }
    @GetMapping("/fuzzy")
    public ResponseEntity<List<Article>> fuzzyWithTitle(@RequestParam String keyword) {
        return new ResponseEntity<>(articleELKService.fuzzyQueryWithTitle(keyword), HttpStatus.OK);
    }
    @GetMapping("/wildcard")
    public ResponseEntity<List<Article>> wildcardWithTitle(@RequestParam String keyword) {
        return new ResponseEntity<>(articleELKService.wildcardQueryWithTitle(keyword),HttpStatus.OK);
    }
    @GetMapping("/range")
    public ResponseEntity<List<Article>> rangeWithPrice(@RequestParam int from, @RequestParam int to) {
        return new ResponseEntity<>(articleELKService.rangeQueryWithPrice(from, to), HttpStatus.OK);
    }

}
