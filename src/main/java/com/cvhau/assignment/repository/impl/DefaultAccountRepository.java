package com.cvhau.assignment.repository.impl;

import com.cvhau.assignment.model.Account;
import com.cvhau.assignment.repository.AccountRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultAccountRepository implements AccountRepository {
    private final AtomicLong lastSavedId;
    private final Map<Long, Account> accounts;

    public DefaultAccountRepository() {
        this(Collections.emptyMap());
    }

    public DefaultAccountRepository(Map<Long, Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public Account save(Account entity) {
        return null;
    }

    @Override
    public Collection<Account> saveAll(Collection<Account> entities) {
        return List.of();
    }

    @Override
    public Optional<Account> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Collection<Account> findAllById(Iterable<Long> longs) {
        return List.of();
    }
}
