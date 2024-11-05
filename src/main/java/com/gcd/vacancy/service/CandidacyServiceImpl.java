package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.ApplicationSentDto;
import com.gcd.vacancy.dto.CandidacyDto;
import com.gcd.vacancy.entity.CandidacyEntity;
import com.gcd.vacancy.entity.CandidateEntity;
import com.gcd.vacancy.entity.EnterpriseEntity;
import com.gcd.vacancy.entity.VacancyEntity;
import com.gcd.vacancy.exceptions.customExceptions.NotFoundException;
import com.gcd.vacancy.exceptions.customExceptions.ResourceAlreadyExistsException;
import com.gcd.vacancy.mapper.CandidacyMapper;
import com.gcd.vacancy.repository.CandidacyRepository;
import com.gcd.vacancy.repository.CandidateRepository;
import com.gcd.vacancy.repository.EnterpriseRepository;
import com.gcd.vacancy.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidacyServiceImpl implements CandidacyService {

    @Autowired
    private CandidacyRepository candidacyRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private CandidacyMapper candidacyMapper;

    @Autowired
    private CandidateNotFoundValidation candidateValidationAlreadyExists;

    @Autowired
    private EnterpriseNotFoundValidation enterpriseNotFoundValidation;

    @Override
    public ApplicationSentDto applyToVacancy(Long vacancyId, Long candidateId) {

        VacancyEntity vacancy = findVacancyById(vacancyId);
        CandidateEntity candidate = candidateValidationAlreadyExists.findCandidateById(candidateId);

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
        newCandidacy.setEnterpriseId(vacancy.getEnterpriseId());

        candidacyRepository.save(newCandidacy);

        return candidacyMapper.toApplicationSentDto(newCandidacy);
    }

    @Override
    public List<CandidacyDto> getCandidacyByEnterpriseId(Long enterpriseId) {
        enterpriseNotFoundValidation.findEnterpriseById(enterpriseId);

        return candidacyMapper.toCandidacyListDto(candidacyRepository.findByEnterpriseId(enterpriseId));
    }

    @Override
    public List<CandidacyDto> getCandidacyByCandidateId(Long candidateId) {
        candidateValidationAlreadyExists.findCandidateById(candidateId);

        return candidacyMapper.toCandidacyListDto(candidacyRepository.findByCandidateId(candidateId));
    }


    private VacancyEntity findVacancyById(Long vacancyId) {
        return vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new NotFoundException("Vaga com id " + vacancyId + " não encontrada."));
    }


}
