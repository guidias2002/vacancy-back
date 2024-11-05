package com.gcd.vacancy.dto;

import com.gcd.vacancy.entity.CurriculumEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CandidateWithCurriculumDto {

    private Long id;
    private String name;
    private String login;
    private String password;
    private String email;
    private String accountType;
    private LocalDateTime createdAt;
    private CurriculumDto curriculum;
}
