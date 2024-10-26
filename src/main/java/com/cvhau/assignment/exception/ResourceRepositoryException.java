package com.cvhau.assignment.exception;

import java.io.Serial;

public class ResourceRepositoryException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -3446612332803033080L;

    public ResourceRepositoryException(String message) {
        super(message);
    }

    public ResourceRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
