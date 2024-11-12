package com.gcd.vacancy.dto;

import lombok.Data;

@Data
public class ProfessionalExperienceDto {

    private Long id;
    private String enterprise;
    private String position;
    private String monthStart;
    private String yearStart;
    private String monthEnd;
    private String yearEnd;
    private Boolean isCurrentJob;
    private String description;
}
