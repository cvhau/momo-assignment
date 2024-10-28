package com.cvhau.assignment.repository.impl;

import com.cvhau.assignment.entity.Provider;
import com.cvhau.assignment.repository.ProviderRepository;

public class ProviderRepositoryImpl extends SerializableResourceRepository<Provider, Long>
        implements ProviderRepository {

    @Override
    public String resourceFilename() {
        return "data/providers.dat";
    }

    @Override
    public Long getNextId() {
        return getLastId().map(id -> id + 1).orElse(1L);
    }
}
