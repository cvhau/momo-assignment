package com.cvhau.assignment.repository.impl;

import com.cvhau.assignment.entity.Bill;
import com.cvhau.assignment.repository.BillRepository;

public class BillRepositoryImpl extends SerializableResourceRepository<Bill, Long> implements BillRepository {

    @Override
    public String resourceFilename() {
        return "data/bills.dat";
    }

    @Override
    public Long getNextId() {
        return getLastId().map(id -> id + 1).orElse(1L);
    }
}
