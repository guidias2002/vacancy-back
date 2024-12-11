package com.gcd.vacancy.service;

import com.gcd.vacancy.entity.RecruiterEntity;
import com.gcd.vacancy.enums.RecruiterInvitationStatus;
import com.gcd.vacancy.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    @Autowired
    private EnterpriseNotFoundValidation enterpriseNotFoundValidation;

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Override
    public RecruiterEntity saveRecruiter(String email, Long enterpriseId) {
        enterpriseNotFoundValidation.findEnterpriseById(enterpriseId);

        String password = PasswordGenerator.generatePassword(10);

        RecruiterEntity recruiterEntity = RecruiterEntity.builder()
        .email(email)
        .password(password)
        .createdAt(LocalDateTime.now())
        .enterpriseId(enterpriseId)
        .invitationStatus(RecruiterInvitationStatus.PENDENTE)
        .build();

        recruiterRepository.save(recruiterEntity);

        return recruiterEntity;
    }
}
