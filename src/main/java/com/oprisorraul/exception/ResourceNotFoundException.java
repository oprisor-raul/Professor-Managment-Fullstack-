package com.oprisorraul.exception;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1;

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}