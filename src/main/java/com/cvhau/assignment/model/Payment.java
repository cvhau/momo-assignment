package com.cvhau.assignment.model;

import java.time.OffsetDateTime;

public class Payment {
    private final long id;
    private final OffsetDateTime paymentDate;
    private PaymentState state;
    private final Bill bill;

    public Payment(long id, OffsetDateTime paymentDate, PaymentState state, Bill bill) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.state = state;
        this.bill = bill;
    }

    public long getId() {
        return id;
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
}
