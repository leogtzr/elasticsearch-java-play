package com.elastic.service;

import com.elastic.model.Account;
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
public class AccountService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    private Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Value("${indexAccount}")
    private String indexName;

    private static final Logger LOG = LoggerFactory.getLogger(AccountService.class);

    public Optional<SearchHit<Account>> dummySearchByFirstName(final String name) {
        final QueryBuilder queryBuilder = QueryBuilders.matchQuery("firstName", name);

        final Query searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();

        final SearchHits<Account> accountsHits =
                elasticsearchOperations.search(searchQuery, Account.class, IndexCoordinates.of(indexName));
        LOG.info(accountsHits.toString());

        return accountsHits.stream().findAny();
        // accountsHits.stream().map(hit -> hit.getContent()).forEach(account -> LOG.info(account.toString()));
    }

    public Optional<SearchHit<Account>> dummySearchByFirstName2(final String name) {
        final String query = """
                    {
                      "match": {
                        "firstname": "%s"
                      }
                    }
                """;

        final String re = String.format(query, name);
        final Query searchQuery = new StringQuery(re);

        final SearchHits<Account> accountsHits = elasticsearchOperations.search(
                searchQuery,
                Account.class,
                IndexCoordinates.of(indexName));

//        accountsHits.stream().map(hit -> hit.getContent()).forEach(account -> {
//            LOG.debug("...");
//            LOG.info(String.format("account -> '%s'", account));
//            LOG.debug(".../>");
//        });
        return accountsHits.stream().findAny();
    }

}
