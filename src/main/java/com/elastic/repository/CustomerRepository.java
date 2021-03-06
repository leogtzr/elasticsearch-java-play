package com.elastic.repository;

import com.elastic.model.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {
    List<Customer> findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
}
