package com.gcd.vacancy.dto;


import com.gcd.vacancy.entity.CandidacyEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CandidateWithApplicationsDto {

    private Long id;
    private String name;
    private String login;
    private String password;
    private String email;
    private String accountType;
    private LocalDateTime createdAt;
    private List<CandidacyEntity> candidacyList;
}
