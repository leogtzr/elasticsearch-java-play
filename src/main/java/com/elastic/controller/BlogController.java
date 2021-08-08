package com.elastic.controller;

import com.elastic.model.website.Blog;
import com.elastic.repository.BlogRepository;
import com.elastic.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/blog")
@RestController
public class BlogController {

    private final Logger LOG = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public Iterable<Blog> findAll() {
        return this.blogRepository.findAll();
    }

    @GetMapping("/{title}")
    public List<Blog> searchByTitle(final @PathVariable String title) {
        return this.blogRepository.findByTitle(title);
    }

    @GetMapping("/id/{id}")
    public Optional<Blog> findById(final @PathVariable String id) {
        final Optional<Blog> byId = this.blogRepository.findById(id);

        if (!byId.isPresent()) {
            LOG.error("Blog not found: {}", id);
            return Optional.empty();
        }

        LOG.info("Blog found: {}", id);
        return byId;
    }

    /*
        curl  -H "Content-Type: application/json" \
            -d '{"title": "abc1", "tags": ["A", "B", "C"], "timestamp": "2021-08-07T19:00:01"}' \
            http://localhost:8080/blog
     */
    @PostMapping
    public Blog createBlog(final @RequestBody Blog blog) {
        LOG.debug("About to insert: {}", blog.toString());

        return this.blogRepository.save(blog);
    }

    @GetMapping("/view/{title}")
    public ResponseEntity<SearchHit<Blog>> byTitle(final @PathVariable String title) {
        final Optional<SearchHit<Blog>> customerSearchHit = this.blogService.searchByTitle(title);
        if (customerSearchHit.isPresent()) {
            return ResponseEntity.ok(customerSearchHit.get());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/view2/{title}")
    public ResponseEntity<SearchHit<Blog>> byTitle2(final @PathVariable String title) {
        final Optional<SearchHit<Blog>> customerSearchHit = this.blogService.searchByTitle2(title);
        if (customerSearchHit.isPresent()) {
            return ResponseEntity.ok(customerSearchHit.get());
        }

        return ResponseEntity.noContent().build();
    }

}
