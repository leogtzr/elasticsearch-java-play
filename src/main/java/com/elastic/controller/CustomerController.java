package com.elastic.controller;

import com.elastic.model.Customer;
import com.elastic.repository.CustomerRepository;
import com.elastic.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public Iterable<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @GetMapping("/{firstName}")
    public List<Customer> byName(final @PathVariable String firstName) {
        return this.customerRepository.findByFirstName(firstName);
    }

    @GetMapping("/id/{id}")
    public Optional<Customer> findById(final @PathVariable String id) {
        final Optional<Customer> byId = this.customerRepository.findById(id);

        if (!byId.isPresent()) {
            logger.error("User not found: {}", id);
            return Optional.empty();
        }

        logger.info("User found: {}", id);
        return byId;
    }

    /*
        curl -X POST -H "Content-Type: application/json" \
            -d '[{"firstName": "Leo", "lastName": "Gtz", "age": 89}]' http://localhost:8080
     */
    @PostMapping("/")
    public int createCustomers(final @RequestBody List<Customer> customers) {
        for (final Customer customer : customers) {
            customer.setDateField(LocalDateTime.now());
        }

        this.customerRepository.saveAll(customers);
        return customers.size();
    }

    @GetMapping("/view/{name}")
    public ResponseEntity<SearchHit<Customer>> byFirstName(final @PathVariable String name) {
        final Optional<SearchHit<Customer>> customerSearchHit = this.customerService.dummySearchByFirstName(name);
        if (customerSearchHit.isPresent()) {
            return ResponseEntity.ok(customerSearchHit.get());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/view2/{name}")
    public ResponseEntity<SearchHit<Customer>> byFirstName2(final @PathVariable String name) {
        final Optional<SearchHit<Customer>> customerSearchHit = this.customerService.dummySearchByFirstName2(name);
        if (customerSearchHit.isPresent()) {
            return ResponseEntity.ok(customerSearchHit.get());
        }

        return ResponseEntity.noContent().build();
    }

}
