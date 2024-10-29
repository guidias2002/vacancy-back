package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.ApplicationSentDto;
import com.gcd.vacancy.entity.CandidacyEntity;
import com.gcd.vacancy.entity.CandidateEntity;
import com.gcd.vacancy.entity.VacancyEntity;
import com.gcd.vacancy.exceptions.customExceptions.NotFoundException;
import com.gcd.vacancy.exceptions.customExceptions.ResourceAlreadyExistsException;
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

        VacancyEntity vacancy = findVacancyById(vacancyId);
        CandidateEntity candidate = findCandidateById(candidateId);

        boolean alreadyApplied = candidacyRepository.existsByCandidateIdAndVacancyId(candidateId, vacancyId);

        if (alreadyApplied) {
            throw new ResourceAlreadyExistsException("O candidato já se inscreveu na vaga " + "'" + vacancy.getTitle() + "'.");
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

    private VacancyEntity findVacancyById(Long vacancyId) {
        return vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new NotFoundException("Vaga com id " + vacancyId + " não encontrada."));
    }

    private CandidateEntity findCandidateById(Long candidateId) {
        return candidateRepository.findById(candidateId)
                .orElseThrow(() -> new NotFoundException("Candidato com id " + candidateId + " não encontrada."));
    }


}
