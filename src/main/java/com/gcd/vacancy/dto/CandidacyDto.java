package com.gcd.vacancy.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CandidacyDto {

    private Long id;
    private Long candidateId;
    private Long vacancyId;
    private String candidateName;
    private String vacancyTitle;
    private String enterpriseName;
    private Long enterpriseId;
    private LocalDateTime createdAt;
}
