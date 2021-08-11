package com.elastic.service;

import com.elastic.model.Customer;
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
public class CustomerService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Value("${indexName}")
    private String indexName;

    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    public Optional<SearchHit<Customer>> searchByFirstName(final String name) {
        final QueryBuilder queryBuilder = QueryBuilders.matchQuery("firstName", name);

        final Query searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();

        final SearchHits<Customer> customerHits =
                elasticsearchOperations.search(searchQuery, Customer.class, IndexCoordinates.of(indexName));
        LOG.info(customerHits.toString());

        return customerHits.stream().findAny();
    }

    public Optional<SearchHit<Customer>> searchByFirstName2(final String name) {
        final String query = """
            {
              "match": {
                "firstname": {
                  "query": "%s"
                }
              }
            }
        """;
        final Query searchQuery = new StringQuery(String.format(query, name));

        final SearchHits<Customer> customerHits = elasticsearchOperations.search(
                searchQuery,
                Customer.class,
                IndexCoordinates.of(indexName));

        return customerHits.stream().findAny();
    }

}
