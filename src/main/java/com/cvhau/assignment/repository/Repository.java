package com.cvhau.assignment.repository;

import java.util.Optional;

public interface Repository<T, ID> {
    <S extends T> S save(S entity);

    Optional<T> findById(ID id);

    Iterable<T> findAllById(Iterable<ID> ids);
}
