package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.ApplicationSentDto;
import com.gcd.vacancy.entity.CandidacyEntity;
import org.springframework.stereotype.Service;

@Service
public interface CandidacyService {

    ApplicationSentDto applyToVacancy(Long vacancyId, Long candidateId);
}
