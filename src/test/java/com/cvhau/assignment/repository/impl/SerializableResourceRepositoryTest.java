package com.cvhau.assignment.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.*;

class SerializableResourceRepositoryTest {
    private SerializableResourceRepository<String, Long> repository;

    @BeforeEach
    void setUp() {
        repository = new SerializableResourceRepositoryImpl<>("");
    }

    @Test
    void loadData() {
    }

    @Test
    void commitData() {
    }
}
