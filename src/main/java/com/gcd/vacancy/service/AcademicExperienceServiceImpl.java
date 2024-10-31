package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.AcademicExperiencePostDto;
import com.gcd.vacancy.entity.AcademicExperienceEntity;
import com.gcd.vacancy.entity.CandidateEntity;
import com.gcd.vacancy.mapper.AcademicExperienceMapper;
import com.gcd.vacancy.repository.AcademicExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AcademicExperienceServiceImpl implements AcademicExperienceService {

    @Autowired
    private AcademicExperienceRepository academicExperienceRepository;

    @Autowired
    private AcademicExperienceMapper academicExperienceMapper;

    @Autowired
    private CurriculumServiceImpl curriculumService;

    @Autowired
    private CandidateServiceImpl candidateServiceImpl;


    @Override
    public void saveAcademicExperience(Long candidateId, AcademicExperiencePostDto academicExperiencePostDto) {
        CandidateEntity candidate = candidateServiceImpl.findCandidateOrElseThrow(candidateId);

        AcademicExperienceEntity academicExperienceEntity = academicExperienceMapper.toAcaAcademicExperienceEntity(academicExperiencePostDto);

        academicExperienceRepository.save(academicExperienceEntity);

        curriculumService.associateAcademicExperienceWithCurriculum(academicExperienceEntity, candidate);
    }
}
