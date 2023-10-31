package com.hoangdz02.ArticleElasticSearchApplication.repository;

import com.hoangdz02.ArticleElasticSearchApplication.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleELKRepository extends ElasticsearchRepository<Article, Long> {
}
