package com.gcd.vacancy.exceptions;

import com.gcd.vacancy.exceptions.customExceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(404, ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExists(ResourceAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(409, ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectCredentials(IncorrectCredentialsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(401, ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(NullValueException.class)
    public ResponseEntity<ErrorResponse> handleNullValue(NullValueException ex) {
        ErrorResponse errorResponse = new ErrorResponse(400, ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(InvalidDateFieldException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDataException(InvalidDateFieldException ex) {
        ErrorResponse errorResponse = new ErrorResponse(400, ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.badRequest().body(errors);
    }


}
