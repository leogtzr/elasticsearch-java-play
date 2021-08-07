package com.elastic.repository;

import com.elastic.model.website.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BlogRepository extends ElasticsearchRepository<Blog, String> {
    List<Blog> findByTitle(String title);
}
