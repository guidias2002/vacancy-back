package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.ApplicationSentDto;
import com.gcd.vacancy.dto.CandidacyDto;
import com.gcd.vacancy.entity.CandidacyEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidacyService {

    ApplicationSentDto applyToVacancy(Long vacancyId, Long candidateId);

    List<CandidacyDto> getCandidacyByEnterpriseId(Long enterpriseId);

    List<CandidacyDto> getCandidacyByCandidateId(Long candidateId);
}
