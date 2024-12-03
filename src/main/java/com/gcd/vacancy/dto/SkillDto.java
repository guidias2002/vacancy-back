package com.gcd.vacancy.dto;

import lombok.Data;

@Data
public class SkillDto {

    private Long id;
    private String skill;
    private Long candidateId;
}
