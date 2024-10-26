package com.cvhau.assignment.exception;

import java.io.Serial;

public class ResourcePermissionException extends ResourceRepositoryException {
    @Serial
    private static final long serialVersionUID = 8521610313200449059L;

    public ResourcePermissionException(String message) {
        super(message);
    }
}
