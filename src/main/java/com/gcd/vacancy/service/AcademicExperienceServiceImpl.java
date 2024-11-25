package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.AcademicExperienceDto;
import com.gcd.vacancy.dto.AcademicExperiencePostDto;
import com.gcd.vacancy.dto.AcademicExperienceUpdateDto;
import com.gcd.vacancy.entity.AcademicExperienceEntity;
import com.gcd.vacancy.entity.CandidateEntity;
import com.gcd.vacancy.exceptions.customExceptions.InvalidDateFieldException;
import com.gcd.vacancy.exceptions.customExceptions.NotFoundException;
import com.gcd.vacancy.exceptions.customExceptions.NullValueException;
import com.gcd.vacancy.mapper.AcademicExperienceMapper;
import com.gcd.vacancy.repository.AcademicExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;


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

    @Autowired
    private CandidateNotFoundValidation candidateValidationAlreadyExists;


    @Override
    public void saveAcademicExperience(Long candidateId, AcademicExperiencePostDto academicExperiencePostDto) {
        CandidateEntity candidate = candidateValidationAlreadyExists.findCandidateById(candidateId);

        int monthStart = Integer.parseInt(academicExperiencePostDto.getMonthStart());
        int monthEnd = Integer.parseInt(academicExperiencePostDto.getMonthEnd());

        if(academicExperiencePostDto.getYearStart() > academicExperiencePostDto.getYearEnd() || (academicExperiencePostDto.getYearStart().equals(academicExperiencePostDto.getYearEnd()) && monthStart > monthEnd)) {
            throw new InvalidDateFieldException("Dados inválidos. Verifique os campos mês/ano de início/término.");
        }

        AcademicExperienceEntity academicExperienceEntity = academicExperienceMapper.toAcaAcademicExperienceEntity(academicExperiencePostDto);

        academicExperienceRepository.save(academicExperienceEntity);

        curriculumService.associateAcademicExperienceWithCurriculum(academicExperienceEntity, candidate);
    }

    @Override
    public AcademicExperienceDto updateAcademicExperience(Long academicExperienceId, AcademicExperienceUpdateDto academicExperienceUpdateDto) {
        AcademicExperienceEntity academicExperienceEntity = academicExperienceRepository.findById(academicExperienceId)
                .orElseThrow(() -> new NotFoundException("Experiência acadêmica com id " + academicExperienceId + " não encontrada."));

        updateFieldOrThrowIfEmpty(academicExperienceUpdateDto.getCourse(), "course", academicExperienceEntity::setCourse);
        updateFieldOrThrowIfEmpty(academicExperienceUpdateDto.getInstitution(), "institution", academicExperienceEntity::setInstitution);
        updateFieldOrThrowIfEmpty(academicExperienceUpdateDto.getLevel(), "level", academicExperienceEntity::setLevel);
        updateFieldOrThrowIfEmpty(academicExperienceUpdateDto.getStatus(), "status", academicExperienceEntity::setStatus);
        updateFieldOrThrowIfEmpty(academicExperienceUpdateDto.getMonthStart(), "monthStart", academicExperienceEntity::setMonthStart);
        updateFieldOrThrowIfEmpty(academicExperienceUpdateDto.getYearStart(), "yearStart", academicExperienceEntity::setYearStart);
        updateFieldOrThrowIfEmpty(academicExperienceUpdateDto.getMonthEnd(), "monthEnd", academicExperienceEntity::setMonthEnd);
        updateFieldOrThrowIfEmpty(academicExperienceUpdateDto.getYearEnd(), "yearEnd", academicExperienceEntity::setYearEnd);


        academicExperienceRepository.save(academicExperienceEntity);

        return academicExperienceMapper.toAcademicExperienceDto(academicExperienceEntity);
    }

    @Override
    public void deleteAcademicExperienceById(Long academicExperienceId) {
        AcademicExperienceEntity academicExperienceEntity = academicExperienceRepository.findById(academicExperienceId)
                .orElseThrow(() -> new NotFoundException("Experiência acadêmica com id " + academicExperienceId + " não encontrada."));

        academicExperienceRepository.deleteById(academicExperienceId);
    }


    // para string
    private void updateFieldOrThrowIfEmpty(String newValue, String fieldName, Consumer<String> setter) {
        Optional.ofNullable(newValue)
                .filter(value -> !value.trim().isEmpty())
                .ifPresentOrElse(setter, () -> {
                    throw new NullValueException("Preencha o campo " + fieldName + ".");
                });
    }

    // para long
    private void updateFieldOrThrowIfEmpty(Long newValue, String fieldName, Consumer<Long> setter) {
        Optional.ofNullable(newValue)
                .ifPresentOrElse(setter, () -> {
                    throw new NullValueException("Preencha o campo " + fieldName + ".");
                });
    }
}
