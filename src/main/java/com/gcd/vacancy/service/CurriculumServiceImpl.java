package com.gcd.vacancy.service;

import com.gcd.vacancy.entity.*;
import com.gcd.vacancy.repository.CandidateRepository;
import com.gcd.vacancy.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurriculumServiceImpl {

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    private CurriculumEntity checkIfThereIsACurriculum(CandidateEntity candidate) {
        CurriculumEntity curriculum = candidate.getCurriculum();

        if(curriculum == null) {
            curriculum = new CurriculumEntity();
            curriculumRepository.save(curriculum);
        }

        return curriculum;
    }


    public void associateAboutMeWithCurriculum(AboutMeEntity aboutMeEntity, CandidateEntity candidate) {
        CurriculumEntity curriculum = checkIfThereIsACurriculum(candidate);

        aboutMeEntity.setCurriculumId(curriculum.getId());

        curriculum.setAboutMe(aboutMeEntity);
        curriculumRepository.save(curriculum);

        candidate.setCurriculum(curriculum);
        candidateRepository.save(candidate);
    }

    public void associateAcademicExperienceWithCurriculum(AcademicExperienceEntity academicExperienceEntity, CandidateEntity candidate) {
        CurriculumEntity curriculum = checkIfThereIsACurriculum(candidate);

        academicExperienceEntity.setCurriculumId(curriculum.getId());
        academicExperienceEntity.setCandidateId(candidate.getId());

        curriculum.getAcademicExperienceList().add(academicExperienceEntity);
        curriculumRepository.save(curriculum);

        candidate.setCurriculum(curriculum);
        candidateRepository.save(candidate);
    }

    public void associateProfessionalExperienceWithCurriculum(ProfessionalExperienceEntity professionalExperienceEntity, CandidateEntity candidate) {
        CurriculumEntity curriculum = checkIfThereIsACurriculum(candidate);

        professionalExperienceEntity.setCandidateId(candidate.getId());
        professionalExperienceEntity.setCurriculumId(curriculum.getId());

        curriculum.getProfessionalExperienceList().add(professionalExperienceEntity);
        curriculumRepository.save(curriculum);

        candidate.setCurriculum(curriculum);
        candidateRepository.save(candidate);
    }
}
















