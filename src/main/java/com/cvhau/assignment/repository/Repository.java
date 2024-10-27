package com.cvhau.assignment.repository;

import java.util.Collection;
import java.util.Optional;

public interface Repository<T, ID> {
    T save(T entity);

    Collection<T> saveAll(Collection<T> entities);

    Optional<T> findById(ID id);

    Collection<T> findAllById(Collection<ID> ids);
}
