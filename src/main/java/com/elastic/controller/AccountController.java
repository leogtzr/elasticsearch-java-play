package com.elastic.controller;

import com.elastic.model.Account;
import com.elastic.repository.AccountRepository;
import com.elastic.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/account")
@RestController
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public Iterable<Account> findAll() {
        return this.accountRepository.findAll();
    }

    @GetMapping("/{firstName}")
    public List<Account> byName(final @PathVariable String firstName) {
        return this.accountRepository.findByFirstName(firstName);
    }

    @GetMapping("/id/{id}")
    public Optional<Account> findById(final @PathVariable String id) {
        final Optional<Account> byId = this.accountRepository.findById(id);

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
    public int createAccount(final @RequestBody List<Account> accounts) {
//        for (final Account customer : customers) {
//            customer.setDateField(LocalDateTime.now());
//        }

        this.accountRepository.saveAll(accounts);
        return accounts.size();
    }

    @GetMapping("/view/{name}")
    public ResponseEntity<SearchHit<Account>> byFirstName(final @PathVariable String name) {
        final Optional<SearchHit<Account>> customerSearchHit = this.accountService.searchByFirstName(name);
        if (customerSearchHit.isPresent()) {
            return ResponseEntity.ok(customerSearchHit.get());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/view2/{name}")
    public ResponseEntity<SearchHit<Account>> byFirstName2(final @PathVariable String name) {
        final Optional<SearchHit<Account>> customerSearchHit = this.accountService.searchByFirstName2(name);
        if (customerSearchHit.isPresent()) {
            return ResponseEntity.ok(customerSearchHit.get());
        }

        return ResponseEntity.noContent().build();
    }

}
