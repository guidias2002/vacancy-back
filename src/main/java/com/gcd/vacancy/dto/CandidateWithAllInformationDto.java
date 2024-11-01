package com.gcd.vacancy.dto;

import com.gcd.vacancy.entity.CandidacyEntity;
import com.gcd.vacancy.entity.CurriculumEntity;
import com.gcd.vacancy.enums.AccountType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CandidateWithAllInformationDto {

    private Long id;
    private String name;
    private String login;
    private String password;
    private String email;
    private AccountType accountType;
    private LocalDateTime createdAt;
    private List<CandidacyEntity> candidacyList;
    private CurriculumEntity curriculum;
}
