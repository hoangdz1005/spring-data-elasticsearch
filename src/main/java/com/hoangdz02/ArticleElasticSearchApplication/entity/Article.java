package com.hoangdz02.ArticleElasticSearchApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "articles")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {
    private Long id;
    private String title;
    private String description;
    private String author;
    private List<String> tags;
    private boolean published;
    private int price;
}

