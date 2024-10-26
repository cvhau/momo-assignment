package com.cvhau.assignment.exception;

import java.io.Serial;

public class ResourceNotFoundException extends ResourceRepositoryException {
    @Serial
    private static final long serialVersionUID = 3424127153279010115L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
