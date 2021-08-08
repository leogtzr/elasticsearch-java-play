package com.elastic.service;

import com.elastic.model.website.Blog;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    private Logger logger = LoggerFactory.getLogger(BlogService.class);

    @Value("${indexBlog}")
    private String indexName;

    private static final Logger LOG = LoggerFactory.getLogger(BlogService.class);

    public Optional<SearchHit<Blog>> searchByTitle(final String title) {
        final QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", title);

        final Query searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();

        final SearchHits<Blog> accountsHits =
                elasticsearchOperations.search(searchQuery, Blog.class, IndexCoordinates.of(indexName));
        LOG.info(accountsHits.toString());

        return accountsHits.stream().findAny();
        // accountsHits.stream().map(hit -> hit.getContent()).forEach(account -> LOG.info(account.toString()));
    }

    public Optional<SearchHit<Blog>> searchByTitle2(final String title) {
        final String query = """
                    {
                      "match": {
                        "title": "%s"
                      }
                    }
                """;

        final String re = String.format(query, title);
        final Query searchQuery = new StringQuery(re);

        final SearchHits<Blog> accountsHits = elasticsearchOperations.search(
                searchQuery,
                Blog.class,
                IndexCoordinates.of(indexName));

//        accountsHits.stream().map(hit -> hit.getContent()).forEach(account -> {
//            LOG.debug("...");
//            LOG.info(String.format("account -> '%s'", account));
//            LOG.debug(".../>");
//        });
        return accountsHits.stream().findAny();
    }

}
