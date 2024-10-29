package com.gcd.vacancy.exceptions.customExceptions;

public class VacancyNotFoundException extends RuntimeException {

    public VacancyNotFoundException(Long id) {
        super("Vaga com id " + id + " não foi encontrada.");
    }
}