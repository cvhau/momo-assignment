package com.cvhau.assignment.service;

import com.cvhau.assignment.entity.Account;

import java.math.BigDecimal;

public interface AccountService {
    /**
     * Create an account.
     * @param name Account name to create.
     * @param amount Balance in the account.
     * @return Created {@link Account} object.
     */
    Account createAccount(String name, BigDecimal amount);

    /**
     * Create an account with no funds by default.
     * @param name Account name to create.
     * @return Created {@link Account} object.
     */
    default Account createAccount(String name) {
        return createAccount(name, BigDecimal.valueOf(0));
    }

    /**
     * Add fund to an account by given account ID and amount of money.
     * @param accountId The account ID
     * @param amount The amount of money
     * @return Updated {@link Account} object.
     */
    Account addFunds(Long accountId, BigDecimal amount);
}
