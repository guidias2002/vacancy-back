package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.ProfessionalExperienceDto;
import com.gcd.vacancy.dto.ProfessionalExperiencePostDto;
import com.gcd.vacancy.dto.ProfessionalExperienceUpdateDto;
import com.gcd.vacancy.entity.CandidateEntity;
import com.gcd.vacancy.entity.ProfessionalExperienceEntity;
import com.gcd.vacancy.enums.Month;
import com.gcd.vacancy.exceptions.customExceptions.InvalidDateFieldException;
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
    public void saveProfessionalExperience(Long candidateId, ProfessionalExperiencePostDto professionalExperiencePostDto) {
        CandidateEntity candidate = candidateValidationAlreadyExists.findCandidateById(candidateId);

        int monthStart = Month.getMonthValue(professionalExperiencePostDto.getMonthStart());
        int monthEnd = Month.getMonthValue(professionalExperiencePostDto.getMonthEnd());

        if(professionalExperiencePostDto.getYearStart() > professionalExperiencePostDto.getYearEnd() || (professionalExperiencePostDto.getYearStart().equals(professionalExperiencePostDto.getYearEnd()) && monthStart > monthEnd)) {
            throw new InvalidDateFieldException("Dados inválidos. Verifique os campos mês/ano de início/término.");
        }

        Integer monthValue = Month.getMonthValue(professionalExperiencePostDto.getMonthEnd());

        if (professionalExperiencePostDto.getIsCurrentJob()) {
            if (monthValue != null || (professionalExperiencePostDto.getYearEnd() != null)) {
                throw new InvalidDateFieldException("Trabalho atual não deve ter Mês/Ano de término preenchidos.");
            }
        } else {
            if (monthValue == null || professionalExperiencePostDto.getYearEnd() == null) {
                throw new InvalidDateFieldException("Campos Mês/Ano de término são obrigatórios quando não é trabalho atual.");
            }
        }

        ProfessionalExperienceEntity professionalExperienceEntity = professionalExperienceMapper.toProfessionalExperienceEntity(professionalExperiencePostDto);

        professionalExperienceRepository.save(professionalExperienceEntity);

        curriculumService.associateProfessionalExperienceWithCurriculum(professionalExperienceEntity, candidate);
    }

    @Override
    public ProfessionalExperienceDto findProfessionalExperienceById(Long professionalExperienceId) {
        ProfessionalExperienceEntity professionalExperienceEntity = professionalExperienceRepository.findById(professionalExperienceId)
                .orElseThrow(() -> new NotFoundException("Experiência profissional com id " + professionalExperienceId + " não encontrada."));

        return professionalExperienceMapper.toProfessionalExperienceDto(professionalExperienceEntity);
    }

    @Override
    public ProfessionalExperienceDto updateProfessionalExperience(Long professionalExperienceId, ProfessionalExperienceUpdateDto professionalExperienceUpdateDto) {
        ProfessionalExperienceEntity professionalExperienceEntity = professionalExperienceRepository.findById(professionalExperienceId)
                .orElseThrow(() -> new NotFoundException("Experiência profissional com id " + professionalExperienceId + " não encontrada."));

        int monthStart = Month.getMonthValue(professionalExperienceUpdateDto.getMonthStart());
        int monthEnd = Month.getMonthValue(professionalExperienceUpdateDto.getMonthEnd());

        if(professionalExperienceUpdateDto.getYearStart() > professionalExperienceUpdateDto.getYearEnd() || (professionalExperienceUpdateDto.getYearStart().equals(professionalExperienceUpdateDto.getYearEnd()) && monthStart > monthEnd)) {
            throw new InvalidDateFieldException("Dados inválidos. Verifique os campos mês/ano de início/término.");
        }

        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getDescription(), "description", professionalExperienceEntity::setDescription);
        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getPosition(), "position", professionalExperienceEntity::setPosition);
        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getEnterprise(), "enterprise", professionalExperienceEntity::setEnterprise);
        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getMonthStart(), "monthStart", professionalExperienceEntity::setMonthStart);
        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getYearStart(), "yearStart", professionalExperienceEntity::setYearStart);
        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getMonthEnd(), "monthEnd", professionalExperienceEntity::setMonthEnd);
        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getYearEnd(), "monthEnd", professionalExperienceEntity::setYearEnd);
        updateFieldOrThrowIfEmpty(professionalExperienceUpdateDto.getIsCurrentJob(), "isCurrentJob", professionalExperienceEntity::setIsCurrentJob);

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

    private void updateFieldOrThrowIfEmpty(Long newValue, String fieldName, Consumer<Long> setter) {
        Optional.ofNullable(newValue)
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

    private void validateEndDateIsNotBeforeStartDate(Long yearStart, Long yearEnd) {
        if (yearEnd < yearStart || (yearEnd.equals(yearStart))) {
            throw new IllegalArgumentException("A data de término não pode ser anterior à data de início.");
        }
    }

}
