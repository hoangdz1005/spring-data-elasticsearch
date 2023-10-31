package com.hoangdz02.ArticleElasticSearchApplication.service;

import com.hoangdz02.ArticleElasticSearchApplication.entity.Article;
import com.hoangdz02.ArticleElasticSearchApplication.repository.ArticleELKRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final ArticleELKRepository articleELKRepository;
    public ArticleService(ArticleELKRepository articleELKRepository) {
        this.articleELKRepository = articleELKRepository;
    }
    public Iterable<Article> getArticles() {
        return articleELKRepository.findAll();
    }
    public Article getArticle(Long id) {
        return articleELKRepository.findById(id).get();
    }
    public Article addArticle(Article article) {
        return articleELKRepository.save(article);
    }
    public Article updateArticle(Long id, Article article) {
        Article article_ = articleELKRepository.findById(id).get();
        article_.setTitle(article.getTitle());
        article_.setDescription(article.getDescription());
        article_.setAuthor(article.getAuthor());
        article_.setTags(article.getTags());
        article_.setPublished(article.isPublished());
        article_.setPrice(article.getPrice());
        return articleELKRepository.save(article_);
    }
    public void deleteArticle(Long id) {
        articleELKRepository.deleteById(id);
    }
}
