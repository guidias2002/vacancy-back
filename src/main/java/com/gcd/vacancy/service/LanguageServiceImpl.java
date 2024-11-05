package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.LanguageDto;
import com.gcd.vacancy.dto.LanguagePostDto;
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

    @Override
    public void saveLanguage(Long candidateId, LanguagePostDto languagePostDto) {
        CandidateEntity candidateEntity = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new NotFoundException("Candidato com id " + candidateId + " não encontrado."));

        Optional<LanguageEntity> languageExists = languageRepository.findByLanguageIgnoreCase(languagePostDto.getLanguage());

        if(languageExists.isPresent()) {
            throw new ResourceAlreadyExistsException("Idioma já cadastrado.");
        }

        LanguageEntity languageEntity = languageRepository.save(languageMapper.toLanguageEntity(languagePostDto));

        curriculumService.associateLanguageWithCurriculum(languageEntity, candidateEntity);
    }

    @Override
    public LanguageDto updateLanguageDto(Long languageId, LanguagePostDto languagePostDto) {
        LanguageEntity languageEntity = languageRepository.findById(languageId)
                .orElseThrow(() -> new NotFoundException("Idioma com id " + languageId + " não encontrado."));

        updateFieldOrThrowIfEmpty(languagePostDto.getLanguage(), "language", languageEntity::setLanguage);
        updateFieldOrThrowIfEmpty(languagePostDto.getLevel(), "level", languageEntity::setLevel);

        languageRepository.save(languageEntity);

        return languageMapper.toLanguageDto(languageEntity);
    }

    @Override
    public void deleteLanguage(Long languageId) {
        LanguageEntity languageEntity = languageRepository.findById(languageId)
                .orElseThrow(() -> new NotFoundException("Linguagem com id " + languageId + " não encontrada."));

        languageRepository.deleteById(languageId);
    }


    private void updateFieldOrThrowIfEmpty(String newValue, String fieldName, Consumer<String> setter) {
        Optional.ofNullable(newValue)
                .filter(value -> !value.trim().isEmpty())
                .ifPresentOrElse(setter, () -> {
                    throw new NullValueException("Preencha o campo " + fieldName + ".");
                });
    }

}
