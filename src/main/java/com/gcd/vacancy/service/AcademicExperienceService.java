package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.AcademicExperiencePostDto;
import org.springframework.stereotype.Service;

@Service
public interface AcademicExperienceService {

    void saveAcademicExperience(Long candidateId, AcademicExperiencePostDto academicExperiencePostDto);
}
