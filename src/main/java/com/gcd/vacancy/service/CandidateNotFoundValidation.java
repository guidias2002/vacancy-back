package com.gcd.vacancy.service;

import com.gcd.vacancy.entity.CandidateEntity;
import com.gcd.vacancy.exceptions.customExceptions.NotFoundException;
import com.gcd.vacancy.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CandidateNotFoundValidation {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity findCandidateById(Long candidateId) {
        return candidateRepository.findById(candidateId)
                .orElseThrow(() -> new NotFoundException("Candidato com id " + candidateId + " não encontrado."));
    }
}
