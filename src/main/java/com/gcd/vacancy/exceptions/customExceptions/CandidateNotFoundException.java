package com.gcd.vacancy.exceptions.customExceptions;

public class CandidateNotFoundException extends RuntimeException {

    public CandidateNotFoundException(Long id) {
        super("Candidato com id " + id + " não foi encontrado");
    }
}
