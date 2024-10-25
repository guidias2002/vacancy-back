package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.ApplicationSentDto;
import com.gcd.vacancy.entity.CandidacyEntity;
import com.gcd.vacancy.entity.CandidateEntity;
import com.gcd.vacancy.entity.VacancyEntity;
import com.gcd.vacancy.repository.CandidacyRepository;
import com.gcd.vacancy.repository.CandidateRepository;
import com.gcd.vacancy.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidacyServiceImpl implements CandidacyService {

    @Autowired
    private CandidacyRepository candidacyRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Override
    public CandidacyEntity applyToVacancy(Long vacancyId, Long candidateId) {

        VacancyEntity vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new IllegalArgumentException("Vacancy not found."));

        CandidateEntity candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found."));

        CandidacyEntity newCandidacy = new CandidacyEntity();

        newCandidacy.setVacancy_id(vacancy.getId());
        newCandidacy.setCandidate_id(candidate.getId());
        newCandidacy.setCandidateName(candidate.getName());
        newCandidacy.setVacancyTitle(vacancy.getTitle());
        newCandidacy.setEnterpriseName(vacancy.getName_enterprise());

        candidacyRepository.save(newCandidacy);

        return newCandidacy;
    }
}
