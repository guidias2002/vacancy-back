package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.ProfessionalExperienceDto;
import com.gcd.vacancy.dto.ProfessionalExperiencePostDto;
import com.gcd.vacancy.dto.ProfessionalExperienceUpdateDto;
import org.springframework.stereotype.Service;

@Service
public interface ProfessionalExperienceService {

    void saveProfessionalExperience(Long candidateId, ProfessionalExperiencePostDto professionalExperiencePostDto);

    ProfessionalExperienceDto updateProfessionalExperience(Long professionalExperienceId, ProfessionalExperienceUpdateDto professionalExperienceUpdateDto);

    void deleteProfessionalExperience(Long professionalExperienceId);
}
