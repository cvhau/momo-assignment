package com.cvhau.assignment.service;

import com.cvhau.assignment.exception.PaymentException;
import com.cvhau.assignment.model.Account;
import com.cvhau.assignment.model.Bill;
import com.cvhau.assignment.model.Payment;

import java.util.Collection;

public interface PaymentService {

    /**
     * Get all payments by given account.
     *
     * @param account Given account to retrieve the payments.
     * @return A collection of {@link Payment} objects.
     */
    Collection<Payment> getAllPaymentsByAccount(Account account);

    /**
     * User able to pay a valid bill using his available fund.
     *
     * @param bill The bill to pay
     * @return Return a {@link Payment} result object.
     * @throws PaymentException If the current bill is invalid or current account hasn't enough fund to pay.
     */
    Payment pay(Bill bill) throws PaymentException;

    /**
     * User able to pay multiple bills at the same time.
     * Payment would be prioritized for bill with early due dates.
     *
     * @param bills A collection of bills to pay.
     * @return A collection of successful {@link Payment}s objects.
     * @throws PaymentException If any bill is invalid or current account hasn't enough fund to pay.
     */
    Collection<Payment> pay(Collection<Bill> bills) throws PaymentException;

    /**
     * User can schedule a payment for a valid bill.
     *
     * @param bill The bill to schedule
     * @return Return a {@link Payment} result object.
     * @throws PaymentException If the current bill is invalid.
     */
    Payment schedule(Bill bill) throws PaymentException;

    /**
     * User can schedule many payments for a collection of valid bills.
     *
     * @param bills A collection of bills to schedule.
     * @return A collection of scheduled {@link Payment}s objects.
     * @throws PaymentException If any bill is invalid.
     */
    Collection<Payment> schedule(Collection<Bill> bills) throws PaymentException;
}
