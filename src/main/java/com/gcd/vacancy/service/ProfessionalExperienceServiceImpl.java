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

import java.util.List;
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

    @Autowired
    private CandidateNotFoundValidation candidateValidationAlreadyExists;

    @Override
    public void saveProfessionalExperience(Long candidateId, List<ProfessionalExperiencePostDto> professionalExperiencePostDtos) {
        CandidateEntity candidateEntity = candidateValidationAlreadyExists.findCandidateById(candidateId);

        List<ProfessionalExperienceEntity> professionalExperienceEntities = professionalExperiencePostDtos.stream()
                .map(professionalExperienceMapper::toProfessionalExperienceEntity)
                .toList();

        for (ProfessionalExperienceEntity experience : professionalExperienceEntities) {
            curriculumService.associateProfessionalExperienceWithCurriculum(experience, candidateEntity);
        }

        // Persistir apenas o candidato, que irá salvar o currículo e suas experiências
        candidateRepository.save(candidateEntity);
    }


    @Override
    public ProfessionalExperienceDto updateProfessionalExperience(Long professionalExperienceId, ProfessionalExperienceUpdateDto professionalExperienceUpdateDto) {
        ProfessionalExperienceEntity professionalExperienceEntity = professionalExperienceRepository.findById(professionalExperienceId)
                .orElseThrow(() -> new NotFoundException("Experiência profissional com id " + professionalExperienceId + " não encontrado."));

        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getDescription(), "description", professionalExperienceEntity::setDescription);
        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getPosition(), "position", professionalExperienceEntity::setPosition);
        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getEnterprise(), "enterprise", professionalExperienceEntity::setEnterprise);
        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getMonthStart(), "monthStart", professionalExperienceEntity::setMonthStart);
        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getYearStart(), "yearStart", professionalExperienceEntity::setYearStart);
        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getMonthEnd(), "monthEnd", professionalExperienceEntity::setMonthEnd);
        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getYearEnd(), "monthEnd", professionalExperienceEntity::setYearEnd);
        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getIsCurrentJob(), "isCurrenteJob", professionalExperienceEntity::setIsCurrentJob);

        professionalExperienceRepository.save(professionalExperienceEntity);

        return professionalExperienceMapper.toProfessionalExperienceDto(professionalExperienceEntity);
    }

    @Override
    public void deleteProfessionalExperience(Long professionalExperienceId) {
        professionalExperienceRepository.findById(professionalExperienceId)
                .orElseThrow(() -> new NotFoundException("Experiência profissional com id " + professionalExperienceId + " não encontrado."));

        professionalExperienceRepository.deleteById(professionalExperienceId);
    }

    @Override
    public List<ProfessionalExperienceDto> findProfessionalExperienceListByCandidate(Long candidateId) {
        candidateValidationAlreadyExists.findCandidateById(candidateId);

        List<ProfessionalExperienceEntity> professionalExperienceEntities = professionalExperienceRepository.findAllByCandidateId(candidateId);

        return professionalExperienceEntities.stream()
                .map(professionalExperienceMapper::toProfessionalExperienceDto)
                .toList();
    }



    private void updateFieldOrThrowIfEmpty(String newValue, String fieldName, Consumer<String> setter) {
        Optional.ofNullable(newValue)
                .filter(value -> !value.trim().isEmpty())
                .ifPresentOrElse(setter, () -> {
                    throw new NullValueException("Preencha o campo " + fieldName + ".");
                });
    }

    private void updateFieldOrThrowIfEmpty(Boolean newValue, String fieldName, Consumer<Boolean> setter) {
        Optional.ofNullable(newValue)
                .ifPresentOrElse(setter, () -> {
                    throw new NullValueException("Preencha o campo " + fieldName + ".");
                });
    }
}
