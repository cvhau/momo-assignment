package com.cvhau.assignment.exception;

import java.io.Serial;

public class PaymentException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4242237370642982431L;

    public PaymentException(String message) {
        super(message);
    }
}
