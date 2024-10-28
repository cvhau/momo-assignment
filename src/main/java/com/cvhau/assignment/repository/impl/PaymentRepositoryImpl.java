package com.cvhau.assignment.repository.impl;

import com.cvhau.assignment.entity.Payment;
import com.cvhau.assignment.repository.PaymentRepository;

public class PaymentRepositoryImpl extends SerializableResourceRepository<Payment, Long> implements PaymentRepository {

    @Override
    public String resourceFilename() {
        return "data/payments.dat";
    }

    @Override
    public Long getNextId() {
        return getLastId().map(id -> id + 1).orElse(1L);
    }
}
