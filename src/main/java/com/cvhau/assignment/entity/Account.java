package com.cvhau.assignment.entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public class Account implements Entity<Long>, Serializable {
    @Serial
    private static final long serialVersionUID = -331708650506603446L;

    private Long id;
    private String name;
    private BigDecimal balance;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
