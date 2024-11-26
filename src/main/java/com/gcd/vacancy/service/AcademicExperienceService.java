package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.AcademicExperienceDto;
import com.gcd.vacancy.dto.AcademicExperiencePostDto;
import com.gcd.vacancy.dto.AcademicExperienceUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AcademicExperienceService {

    void saveAcademicExperience(Long candidateId, AcademicExperiencePostDto academicExperiencePostDto);

    AcademicExperienceDto updateAcademicExperience(Long academicExperienceId, AcademicExperienceUpdateDto academicExperienceUpdateDto);

    void deleteAcademicExperienceById(Long academicExperienceId);

    List<AcademicExperienceDto> findAllAcademicExperienceByCandidateId(Long candidateId);
}
