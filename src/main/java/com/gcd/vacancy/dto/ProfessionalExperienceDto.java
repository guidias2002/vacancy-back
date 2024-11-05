package com.gcd.vacancy.dto;

import lombok.Data;

@Data
public class ProfessionalExperienceDto {

    private Long id;
    private String enterprise;
    private String position;
    private String period;
    private String description;
}
