package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.RecruiterDto;
import com.gcd.vacancy.dto.RecruiterEmailAndPasswordDto;
import com.gcd.vacancy.dto.RecruiterLoginDto;
import com.gcd.vacancy.dto.UpdatedPasswordDto;
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
import java.util.List;
import java.util.Optional;

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

    @Override
    public RecruiterEntity saveRecruiter(String email, Long enterpriseId) {
        EnterpriseEntity enterpriseEntity = enterpriseNotFoundValidation.findEnterpriseById(enterpriseId);

        String password = PasswordGenerator.generatePassword(10);

        RecruiterEntity recruiterEntity = RecruiterEntity.builder()
        .email(email)
        .password(password)
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .enterpriseId(enterpriseId)
        .invitationStatus(RecruiterInvitationStatus.PENDENTE)
        .build();

        recruiterRepository.save(recruiterEntity);

        emailService.sendInvitationToRecruiter(email, password, "Convite de acesso à plataforma", enterpriseEntity.getName());

        return recruiterEntity;
    }

    @Override
    public String loginRecruiter(RecruiterLoginDto recruiterLoginDto) {
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

        return "Logado com sucesso";
    }

    @Override
    public List<RecruiterDto> findAllRecruiters() {

        return recruiterMapper.toRecruiterDtoList(recruiterRepository.findAll());
    }

    @Override
    public List<RecruiterDto> findAllRecruitersByEnterpriseId(Long enterpriseId) {
        enterpriseNotFoundValidation.findEnterpriseById(enterpriseId);

        return recruiterMapper.toRecruiterDtoList(recruiterRepository.findAllRecruiterByEnterpriseId(enterpriseId));
    }

    @Override
    public RecruiterEmailAndPasswordDto updateRecruiterPassword(Long recruiterId, UpdatedPasswordDto updatedPasswordDto) {
        RecruiterEntity recruiterEntity = recruiterRepository.findById(recruiterId)
                .orElseThrow(() -> new NotFoundException("Recrutador com id " + recruiterId + " não encontrado."));

        if(!updatedPasswordDto.getNewPassword().equals(updatedPasswordDto.getNewPasswordConfirmed())) {
            throw new PasswordMismatchException("As senhas fornecidas não são iguais.");
        }

        recruiterEntity.setPassword(updatedPasswordDto.getNewPassword());
        recruiterRepository.save(recruiterEntity);

        return recruiterMapper.toRecruiterEmailAndPasswordDto(recruiterEntity);
    }
}
