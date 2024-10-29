package com.gcd.vacancy.exceptions.customExceptions;

public class EnterpriseNotFoundException extends RuntimeException{

    public EnterpriseNotFoundException(Long id) {
        super("Empresa com ID " + id + " não foi encontrada.");
    }
}
