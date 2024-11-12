package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.AboutMeDto;
import com.gcd.vacancy.dto.AboutMePostDto;
import com.gcd.vacancy.entity.AboutMeEntity;
import com.gcd.vacancy.entity.CandidateEntity;
import com.gcd.vacancy.exceptions.customExceptions.NotFoundException;
import com.gcd.vacancy.exceptions.customExceptions.NullValueException;
import com.gcd.vacancy.exceptions.customExceptions.ResourceAlreadyExistsException;
import com.gcd.vacancy.mapper.AboutMeMapper;
import com.gcd.vacancy.repository.AboutMeRepository;
import com.gcd.vacancy.repository.CandidateRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;

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

    @Autowired
    private CandidateNotFoundValidation candidateNotFoundValidation;


    @Override
    public void saveAboutMe(Long candidateId, AboutMePostDto aboutMePostDto) {
        Boolean existsAboutMe = aboutMeRepository.existsByCandidateId(candidateId);

        if(existsAboutMe) {
            throw new ResourceAlreadyExistsException("About me já está criado.");
        };

        CandidateEntity candidateEntity = candidateNotFoundValidation.findCandidateById(candidateId);

        AboutMeEntity aboutMeEntity = aboutMeMapper.toAboutMeEntity(aboutMePostDto);

        aboutMeEntity.setCandidateId(candidateId);
        aboutMeRepository.save(aboutMeEntity);

        curriculumService.associateAboutMeWithCurriculum(aboutMeEntity, candidateEntity);
    }


    @Override
    public AboutMeDto updateAboutMe(Long candidateId, AboutMePostDto aboutMePostDto) {
        CandidateEntity candidate = candidateNotFoundValidation.findCandidateById(candidateId);

        AboutMeEntity aboutMeEntity = aboutMeRepository.findAboutMeByCandidateId(candidate.getId())
                .orElseThrow(() -> new NotFoundException("O candidato com id " + candidate.getId() + " não possui a sessão 'Sobre mim' criada."));

        updateFieldOrThrowIfEmpty(aboutMePostDto.getFullName(), "fullname", aboutMeEntity::setFullName);
        updateFieldOrThrowIfEmpty(aboutMePostDto.getLocation(), "location", aboutMeEntity::setLocation);
        updateFieldOrThrowIfEmpty(aboutMePostDto.getLinkedin(), "linkedin", aboutMeEntity::setLinkedin);
        updateFieldOrThrowIfEmpty(aboutMePostDto.getCellphoneNumber(), "cellphonenumber", aboutMeEntity::setCellphoneNumber);

        aboutMeRepository.save(aboutMeEntity);

        return aboutMeMapper.toAboutMeDto(aboutMeEntity);
    }

    @Override
    public AboutMeDto findAboutMeByCandidateId(Long candidateId) {
        AboutMeEntity aboutMeEntity = aboutMeRepository.findAboutMeByCandidateId(candidateId)
                .orElseThrow(() -> new NotFoundException("Candidato com id " + candidateId + " não encontrado."));

        return aboutMeMapper.toAboutMeDto(aboutMeEntity);
    }

    private void updateFieldOrThrowIfEmpty(String newValue, String fieldName, Consumer<String> setter) {
        Optional.ofNullable(newValue)
                .filter(value -> !value.trim().isEmpty())
                .ifPresentOrElse(setter, () -> {
                    throw new NullValueException("Preencha o campo " + fieldName + ".");
                });
    }

}
