package org.example.ai.exceptions;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
