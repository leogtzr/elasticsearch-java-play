package com.elastic.service;

import com.elastic.model.Customer;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Value("${indexName}")
    private String indexName;

    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    public void dummySearchByFirstName() {
        final QueryBuilder queryBuilder = QueryBuilders.matchQuery("firstName", "Leonardo");

        final Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        final SearchHits<Customer> customerHits =
                elasticsearchOperations.search(searchQuery, Customer.class, IndexCoordinates.of(indexName));
        LOG.info(customerHits.toString());

        customerHits.stream().map(hit -> hit.getContent()).forEach(customer -> LOG.info(customer.toString()));
    }

    public void dummySearchByFirstName2() {
        final String query = """
            {
              "match": {
                "firstName": {
                  "query": "Leonardo"
                }
              }
            }
        """;
        final Query searchQuery = new StringQuery(query);

        final SearchHits<Customer> customerHits = elasticsearchOperations.search(
                searchQuery,
                Customer.class,
                IndexCoordinates.of(indexName));

        customerHits.stream().map(hit -> hit.getContent()).forEach(customer -> {
            LOG.debug("...");
            LOG.info(String.format("customer -> '%s'", customer));
            LOG.debug(".../>");
        });
    }

}
