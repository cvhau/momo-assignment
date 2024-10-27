package com.cvhau.assignment.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

public class Payment implements Entity<Long>, Serializable {
    @Serial
    private static final long serialVersionUID = -4868717116859035195L;

    private Long id;
    private final OffsetDateTime paymentDate;
    private PaymentState state;
    private final Bill bill;
    private final Account account;

    public Payment(Long id,
                   OffsetDateTime paymentDate,
                   PaymentState state,
                   Bill bill,
                   Account account) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.state = state;
        this.bill = bill;
        this.account = account;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getPaymentDate() {
        return paymentDate;
    }

    public PaymentState getState() {
        return state;
    }

    public void setState(PaymentState state) {
        this.state = state;
    }

    public Bill getBill() {
        return bill;
    }

    public Account getAccount() {
        return account;
    }
}
