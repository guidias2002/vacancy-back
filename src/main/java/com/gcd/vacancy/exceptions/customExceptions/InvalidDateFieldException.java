package com.gcd.vacancy.exceptions.customExceptions;

public class InvalidDateFieldException extends RuntimeException{

    public InvalidDateFieldException(String message) {
        super(message);
    }
}
