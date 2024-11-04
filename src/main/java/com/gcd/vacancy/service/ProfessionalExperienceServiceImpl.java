package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.ProfessionalExperienceDto;
import com.gcd.vacancy.dto.ProfessionalExperiencePostDto;
import com.gcd.vacancy.dto.ProfessionalExperienceUpdateDto;
import com.gcd.vacancy.entity.CandidateEntity;
import com.gcd.vacancy.entity.ProfessionalExperienceEntity;
import com.gcd.vacancy.exceptions.customExceptions.NotFoundException;
import com.gcd.vacancy.exceptions.customExceptions.NullValueException;
import com.gcd.vacancy.mapper.ProfessionalExperienceMapper;
import com.gcd.vacancy.repository.CandidateRepository;
import com.gcd.vacancy.repository.ProfessionalExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;

@Service
public class ProfessionalExperienceServiceImpl implements ProfessionalExperienceService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ProfessionalExperienceRepository professionalExperienceRepository;

    @Autowired
    private ProfessionalExperienceMapper professionalExperienceMapper;

    @Autowired
    private CurriculumServiceImpl curriculumService;


    @Override
    public void saveProfessionalExperience(Long candidateId, ProfessionalExperiencePostDto professionalExperiencePostDto) {
        CandidateEntity candidateEntity = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new NotFoundException("Candidato com id " + candidateId + " não encontrado"));

        ProfessionalExperienceEntity professionalExperienceEntity = professionalExperienceMapper.toProfessionalExperienceEntity(professionalExperiencePostDto);

        curriculumService.associateProfessionalExperienceWithCurriculum(professionalExperienceEntity, candidateEntity);

        professionalExperienceRepository.save(professionalExperienceEntity);
    }

    @Override
    public ProfessionalExperienceDto updateProfessionalExperience(Long professionalExperienceId, ProfessionalExperienceUpdateDto professionalExperienceUpdateDto) {
        ProfessionalExperienceEntity professionalExperienceEntity = professionalExperienceRepository.findById(professionalExperienceId)
                .orElseThrow(() -> new NotFoundException("Experiência profissional com id " + professionalExperienceId + " não encontrado."));

        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getDescription(), "description", professionalExperienceEntity::setDescription);
        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getPosition(), "position", professionalExperienceEntity::setPosition);
        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getEnterprise(), "enterprise", professionalExperienceEntity::setEnterprise);
        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getPeriod(), "period", professionalExperienceEntity::setPeriod);

        professionalExperienceRepository.save(professionalExperienceEntity);

        return professionalExperienceMapper.toProfessionalExperienceDto(professionalExperienceEntity);
    }

    private void updateFieldOrThrowIfEmpty(String newValue, String fieldName, Consumer<String> setter) {
        Optional.ofNullable(newValue)
                .filter(value -> !value.trim().isEmpty())
                .ifPresentOrElse(setter, () -> {
                    throw new NullValueException("Preencha o campo " + fieldName + ".");
                });
    }
}
