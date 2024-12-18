package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.*;
import com.gcd.vacancy.entity.EnterpriseEntity;
import com.gcd.vacancy.entity.RecruiterEntity;
import com.gcd.vacancy.enums.RecruiterInvitationStatus;
import com.gcd.vacancy.exceptions.customExceptions.IncorrectCredentialsException;
import com.gcd.vacancy.exceptions.customExceptions.NotFoundException;
import com.gcd.vacancy.exceptions.customExceptions.PasswordMismatchException;
import com.gcd.vacancy.mapper.RecruiterMapper;
import com.gcd.vacancy.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    @Autowired
    private EnterpriseNotFoundValidation enterpriseNotFoundValidation;

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RecruiterMapper recruiterMapper;

    @Autowired
    private TokenService tokenService;

    @Override
    public RecruiterEntity saveRecruiter(RecruiterPostDto recruiterPostDto, Long enterpriseId) {
        EnterpriseEntity enterpriseEntity = enterpriseNotFoundValidation.findEnterpriseById(enterpriseId);

        String password = PasswordGenerator.generatePassword(10);

        RecruiterEntity recruiterEntity = RecruiterEntity.builder()
        .email(recruiterPostDto.getEmail())
        .name(capitalizeWords(recruiterPostDto.getName()))
        .password(password)
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .enterpriseId(enterpriseId)
        .invitationStatus(RecruiterInvitationStatus.PENDENTE)
        .build();

        recruiterRepository.save(recruiterEntity);

        emailService.sendInvitationToRecruiter(recruiterPostDto.getEmail(), password, "Convite de acesso à plataforma", enterpriseEntity.getName(), recruiterEntity.getName());

        return recruiterEntity;
    }

    @Override
    public Map<String, Object> loginRecruiter(RecruiterLoginDto recruiterLoginDto) {
        RecruiterEntity recruiter = recruiterRepository.findRecruiterByEmail(recruiterLoginDto.getEmail())
                .orElseThrow(() -> new NotFoundException("Email não encontrado"));

        if(!recruiterLoginDto.getPassword().equals(recruiter.getPassword())) {
            throw new IncorrectCredentialsException("Credenciais incorretas.");
        }

        if(recruiter.getInvitationStatus().equals(RecruiterInvitationStatus.PENDENTE)) {
            recruiter.setInvitationStatus(RecruiterInvitationStatus.ATIVO);
            recruiter.setUpdatedAt(LocalDateTime.now());
            recruiterRepository.save(recruiter);
        }

        String token = tokenService.generateToken(recruiter.getEmail());

        Map<String, Object> response = new HashMap<>();

        response.put("token", token);
        response.put("userName", recruiter.getName());
        response.put("userId", recruiter.getId());
        response.put("accountType", recruiter.getAccountType());
        response.put("login", recruiter.getEmail());

        return response;
    }

    @Override
    public List<RecruiterDto> findAllRecruiters() {

        return recruiterMapper.toRecruiterDtoList(recruiterRepository.findAll());
    }

    @Override
    public List<RecruiterDto> findAllRecruitersByEnterpriseId(Long enterpriseId) {
        enterpriseNotFoundValidation.findEnterpriseById(enterpriseId);

        List<RecruiterEntity> listReccruiter = recruiterRepository.findAllRecruiterByEnterpriseId(enterpriseId)
                .stream()
                .sorted(Comparator.comparing(recruiter -> getCustomOrder(recruiter.getInvitationStatus())))
                .collect(Collectors.toList());

        return recruiterMapper.toRecruiterDtoList(listReccruiter);
    }

    @Override
    public RecruiterEmailAndPasswordDto updateRecruiterPassword(Long recruiterId, RecruiterUpdatedPasswordDto updatedPasswordDto) {
        RecruiterEntity recruiterEntity = recruiterRepository.findById(recruiterId)
                .orElseThrow(() -> new NotFoundException("Recrutador com id " + recruiterId + " não encontrado."));

        if(!updatedPasswordDto.getNewPassword().equals(updatedPasswordDto.getNewPasswordConfirmed())) {
            throw new PasswordMismatchException("As senhas fornecidas não são iguais.");
        }

        recruiterEntity.setPassword(updatedPasswordDto.getNewPassword());
        recruiterRepository.save(recruiterEntity);

        return recruiterMapper.toRecruiterEmailAndPasswordDto(recruiterEntity);
    }

    private int getCustomOrder(RecruiterInvitationStatus status) {
        return switch (status) {
            case ATIVO -> 1;
            case PENDENTE -> 2;
            case INATIVO -> 3;
            default -> Integer.MAX_VALUE; //
        };
    }

    private String capitalizeWords(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return Arrays.stream(name.split(" "))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

}
