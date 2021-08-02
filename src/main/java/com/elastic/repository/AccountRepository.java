package com.elastic.repository;

import com.elastic.model.Account;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends ElasticsearchRepository<Account, String> {
    List<Account> findByFirstName(String firstName);
    List<Account> findByLastName(String lastName);
}
