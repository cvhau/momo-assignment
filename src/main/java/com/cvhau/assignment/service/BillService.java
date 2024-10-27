package com.cvhau.assignment.service;

import com.cvhau.assignment.entity.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

public interface BillService {

    Bill createBill(Account account, Provider provider, BillType billType, BigDecimal amount);

    /**
     * User can delete a bill.
     *
     * @param billId The bill ID to delete.
     */
    void deleteBillById(Long billId);

    void updateBillById(Long billId, BillState newState);

    Optional<Bill> findBillById(Long billId);

    Collection<Bill> findAllBillsByAccountAndProvider(Account account, String providerName);

    Collection<Bill> findAllBillsByAccount(Account account);

    // TODO Use findAllValidBillsByAccount method for DUE_DATE

    /**
     * Get a collection of valid bills for tracking.
     * A bill maybe has valid state if it is still not-paid and its due date is not past.
     * @param account The current account
     * @return A collection of valid bills.
     */
    Collection<Bill> findAllValidBillsByAccount(Account account);
}
