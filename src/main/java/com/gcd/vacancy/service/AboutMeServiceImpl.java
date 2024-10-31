package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.AboutMePostDto;
import com.gcd.vacancy.entity.AboutMeEntity;
import com.gcd.vacancy.entity.CandidateEntity;
import com.gcd.vacancy.entity.CurriculumEntity;
import com.gcd.vacancy.exceptions.customExceptions.NotFoundException;
import com.gcd.vacancy.mapper.AboutMeMapper;
import com.gcd.vacancy.repository.AboutMeRepository;
import com.gcd.vacancy.repository.CandidateRepository;
import com.gcd.vacancy.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutMeServiceImpl implements AboutMeService {

    @Autowired
    private AboutMeRepository aboutMeRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private AboutMeMapper aboutMeMapper;

    @Autowired
    private CurriculumServiceImpl curriculumService;

    @Autowired
    private CandidateServiceImpl candidateServiceImpl;


    @Override
    public void saveAboutMe(Long candidateId, AboutMePostDto aboutMePostDto) {
        CandidateEntity candidateEntity = candidateServiceImpl.findCandidateOrElseThrow(candidateId);

        AboutMeEntity aboutMeEntity = aboutMeMapper.toAboutMeEntity(aboutMePostDto);

        aboutMeEntity.setCandidateId(candidateId);
        aboutMeRepository.save(aboutMeEntity);

        curriculumService.associateAboutMeWithCurriculum(aboutMeEntity, candidateEntity);
    }

}
