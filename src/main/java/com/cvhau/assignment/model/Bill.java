package com.cvhau.assignment.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Bill {
    private final long id;
    private final BillType type;
    private final BigDecimal amount;
    private final OffsetDateTime dueDate;
    private BillState state;
    private final Account account;
    private final Provider provider;

    public Bill(long id,
                BillType type,
                BigDecimal amount,
                OffsetDateTime dueDate,
                BillState state,
                Account account,
                Provider provider) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.dueDate = dueDate;
        this.state = state;
        this.account = account;
        this.provider = provider;
    }

    public long getId() {
        return id;
    }

    public BillType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public OffsetDateTime getDueDate() {
        return dueDate;
    }

    public BillState getState() {
        return state;
    }

    public void setState(BillState state) {
        this.state = state;
    }

    public Account getAccount() {
        return account;
    }

    public Provider getProvider() {
        return provider;
    }
}
