package com.gcd.vacancy.dto;

import lombok.Data;

@Data
public class ProfessionalExperienceUpdateDto {

    private String enterprise;
    private String position;
    private String monthStart;
    private Long yearStart;
    private String monthEnd;
    private Long yearEnd;
    private Boolean isCurrentJob;
    private String description;
}
