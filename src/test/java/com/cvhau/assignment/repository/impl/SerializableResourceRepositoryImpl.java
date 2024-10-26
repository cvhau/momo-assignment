package com.cvhau.assignment.repository.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class SerializableResourceRepositoryImpl<T extends Serializable, ID extends Serializable> extends SerializableResourceRepository<T, ID> {

    public SerializableResourceRepositoryImpl(String resourceFilename) {
        super(resourceFilename);
    }

    @Override
    public T save(T entity) {
        return null;
    }

    @Override
    public Collection<T> saveAll(Collection<T> entities) {
        return List.of();
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.empty();
    }

    @Override
    public Collection<T> findAllById(Iterable<ID> ids) {
        return List.of();
    }
}
