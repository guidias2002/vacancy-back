package com.gcd.vacancy.exceptions.customExceptions;

public class UnauthorizedUserException extends RuntimeException {

    public UnauthorizedUserException(String message) {
        super(message);
    }
}
