package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.ApplicationSentDto;
import com.gcd.vacancy.entity.CandidacyEntity;
import com.gcd.vacancy.entity.CandidateEntity;
import com.gcd.vacancy.entity.VacancyEntity;
import com.gcd.vacancy.exceptions.CandidateNotFoundException;
import com.gcd.vacancy.exceptions.VacancyNotFoundException;
import com.gcd.vacancy.mapper.CandidacyMapper;
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

    @Autowired
    private CandidacyMapper candidacyMapper;

    @Override
    public ApplicationSentDto applyToVacancy(Long vacancyId, Long candidateId) {

        VacancyEntity vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new VacancyNotFoundException(vacancyId));

        CandidateEntity candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException(candidateId));

        boolean alreadyApplied = candidacyRepository.existsByCandidateIdAndVacancyId(candidateId, vacancyId);

        if (alreadyApplied) {
            throw new IllegalArgumentException("O candidato já se inscreveu nessa vaga.");
        }

        CandidacyEntity newCandidacy = new CandidacyEntity();

        newCandidacy.setVacancyId(vacancy.getId());
        newCandidacy.setCandidateId(candidate.getId());
        newCandidacy.setCandidateName(candidate.getName());
        newCandidacy.setVacancyTitle(vacancy.getTitle());
        newCandidacy.setEnterpriseName(vacancy.getName_enterprise());

        candidacyRepository.save(newCandidacy);

        ApplicationSentDto candidacyDto = candidacyMapper.toApplicationSentDto(newCandidacy);

        return candidacyDto;
    }

}
