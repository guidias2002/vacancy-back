package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.LanguagePostDto;
import com.gcd.vacancy.entity.CandidateEntity;
import com.gcd.vacancy.entity.LanguageEntity;
import com.gcd.vacancy.exceptions.customExceptions.NotFoundException;
import com.gcd.vacancy.mapper.LanguageMapper;
import com.gcd.vacancy.repository.CandidateRepository;
import com.gcd.vacancy.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements LanguageService{

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private LanguageMapper languageMapper;

    @Autowired
    private CurriculumServiceImpl curriculumService;

    @Override
    public void saveLanguage(Long candidateId, LanguagePostDto languagePostDto) {
        CandidateEntity candidateEntity = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new NotFoundException("Candidato com id " + candidateId + " não encontrado."));

        LanguageEntity languageEntity = languageRepository.save(languageMapper.toLanguageEntity(languagePostDto));

        curriculumService.associateLanguageWithCurriculum(languageEntity, candidateEntity);
    }
}
