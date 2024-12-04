package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.LanguageDto;
import com.gcd.vacancy.dto.LanguagePostDto;
import com.gcd.vacancy.dto.LanguageUpdateDto;
import com.gcd.vacancy.entity.CandidateEntity;
import com.gcd.vacancy.entity.LanguageEntity;
import com.gcd.vacancy.exceptions.customExceptions.NotFoundException;
import com.gcd.vacancy.exceptions.customExceptions.NullValueException;
import com.gcd.vacancy.exceptions.customExceptions.ResourceAlreadyExistsException;
import com.gcd.vacancy.mapper.LanguageMapper;
import com.gcd.vacancy.repository.CandidateRepository;
import com.gcd.vacancy.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

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

    @Autowired
    private CandidateNotFoundValidation candidateValidationAlreadyExists;

    @Override
    public void saveLanguage(Long candidateId, LanguagePostDto languagePostDto) {
        CandidateEntity candidateEntity = candidateValidationAlreadyExists.findCandidateById(candidateId);

        Optional<LanguageEntity> languageExists = languageRepository.findByLanguageIgnoreCase(languagePostDto.getLanguage());

        if(languageExists.isPresent()) {
            throw new ResourceAlreadyExistsException("Idioma já cadastrado.");
        }

        LanguageEntity languageEntity = languageRepository.save(languageMapper.toLanguageEntity(languagePostDto));

        curriculumService.associateLanguageWithCurriculum(languageEntity, candidateEntity);
    }

    @Override
    public void deleteLanguage(Long languageId) {
        languageRepository.findById(languageId)
                .orElseThrow(() -> new NotFoundException("Idioma com id " + languageId + " não encontrada."));

        languageRepository.deleteById(languageId);
    }

    @Override
    public List<LanguageDto> findLanguagesByCandidateId(Long candidateId) {
        candidateValidationAlreadyExists.findCandidateById(candidateId);

        return languageMapper.toLanguageDtoList(languageRepository.findLanguagesByCandidateId(candidateId));
    }

    @Override
    public LanguageDto updateLanguage(Long languageId, LanguageUpdateDto languageUpdateDto) {
        LanguageEntity languageEntity = languageRepository.findById(languageId)
                .orElseThrow(() -> new NotFoundException("Idioma com id " + languageId + " não encontrada."));

        boolean isUpdated = false;

        if(languageUpdateDto.getLanguage() != null && !languageUpdateDto.getLanguage().isEmpty()) {
            languageEntity.setLanguage(languageUpdateDto.getLanguage());
            isUpdated = true;
        }

        if(languageUpdateDto.getLevel() != null && !languageUpdateDto.getLevel().isEmpty()) {
            languageEntity.setLevel(languageUpdateDto.getLevel());
            isUpdated = true;
        }

        if(isUpdated) {
            languageRepository.save(languageEntity);
        }

        return languageMapper.toLanguageDto(languageEntity);
    }


}
