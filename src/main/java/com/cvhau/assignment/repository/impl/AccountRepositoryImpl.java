package com.cvhau.assignment.repository.impl;

import com.cvhau.assignment.entity.Account;
import com.cvhau.assignment.repository.AccountRepository;

public class AccountRepositoryImpl extends SerializableResourceRepository<Account, Long> implements AccountRepository {

    public AccountRepositoryImpl() {
        super();
        loadData();
    }

    @Override
    public String resourceFilename() {
        return "data/accounts.dat";
    }

    @Override
    public Long getNextId() {
        return getLastId().map(id -> id + 1).orElse(1L);
    }
}
