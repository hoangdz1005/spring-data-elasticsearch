package com.hoangdz02.ArticleElasticSearchApplication.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.json.JsonData;
import com.hoangdz02.ArticleElasticSearchApplication.entity.Article;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;

import java.util.List;

import static co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders.match;

@Service
public class ArticleELKService {
    private final ElasticsearchTemplate elasticsearchTemplate;
    public ArticleELKService(ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    // 1. Match Query
    public List<Article> matchQueryWithTitle (String keyword) {
        var criteria = match(b -> b.field("title").query(keyword));
        return elasticsearchTemplate.search(NativeQuery.builder().withQuery(criteria).build(), Article.class)
                .stream().map(SearchHit::getContent).toList();
    }

    // 2. Multi-Match Query
    public List<Article> multiMatchQueryWithTitleAndTag(String keyword) {
        var criteria = QueryBuilders.multiMatch(b->b.fields("title", "tags").query(keyword));
        return elasticsearchTemplate.search(NativeQuery.builder().withQuery(criteria).build(), Article.class)
                .stream().map(SearchHit::getContent).toList();
    }

    // 3. Term Query
    public List<Article> termQueryWithTitle(String keyword) {
        var criteria = QueryBuilders.term(b->b.field("title").value(keyword));
        return elasticsearchTemplate.search(NativeQuery.builder().withQuery(criteria).build(), Article.class)
                .stream().map(SearchHit::getContent).toList();
    }

    // 4. Bool Query
    public List<Article> boolQueryWithTitleAndTagAndPublished(String title, String tag, boolean isPublished) {
        var criteria = QueryBuilders.bool(builder -> builder
                        .must(match(q-> q.field("title").query(title)))
                        .must(match(q-> q.field("published").query(isPublished)))
                        .should(match(q-> q.field("tag").query(tag)))
        );
        return elasticsearchTemplate.search(NativeQuery.builder().withQuery(criteria).build(), Article.class)
                .stream().map(SearchHit::getContent).toList();
    }

    // 5. Range Query
    public List<Article> rangeQueryWithPrice(int from, int to) {
        var criteria = QueryBuilders.range(b -> b.field("price").lte(JsonData.of(to)).gte(JsonData.of(from)));
        return elasticsearchTemplate.search(NativeQuery.builder().withQuery(criteria).build(), Article.class)
                .stream().map(SearchHit::getContent).toList();
    }

    // 6. Fuzzy Query
    public List<Article> fuzzyQueryWithTitle(String keyword) {
        var criteria = QueryBuilders.fuzzy(b->b.field("title").value(keyword));
        return elasticsearchTemplate.search(NativeQuery.builder().withQuery(criteria).build(), Article.class)
                .stream().map(SearchHit::getContent).toList();
    }

    // 7. Wildcard Query
    public List<Article> wildcardQueryWithTitle(String keyword) {
        var criteria = QueryBuilders.wildcard(b->b.field("title").value(keyword));
        return elasticsearchTemplate.search(NativeQuery.builder().withQuery(criteria).build(), Article.class)
                .stream().map(SearchHit::getContent).toList();
    }


}

