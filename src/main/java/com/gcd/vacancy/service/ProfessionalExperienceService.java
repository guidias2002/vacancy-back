package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.ProfessionalExperienceDto;
import com.gcd.vacancy.dto.ProfessionalExperiencePostDto;
import com.gcd.vacancy.dto.ProfessionalExperienceUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfessionalExperienceService {

    void saveProfessionalExperience(Long candidateId, List<ProfessionalExperiencePostDto> professionalExperiencePostDtos);

    ProfessionalExperienceDto updateProfessionalExperience(Long professionalExperienceId, ProfessionalExperienceUpdateDto professionalExperienceUpdateDto);

    void deleteProfessionalExperience(Long professionalExperienceId);

    List<ProfessionalExperienceDto> findProfessionalExperienceListByCandidate(Long candidateId);

}
