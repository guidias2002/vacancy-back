package com.gcd.vacancy.dto;

import lombok.Data;

@Data
public class AcademicExperienceDto {

    private String course;
    private String institution;
    private String level;
    private String status;
    private String monthStart;
    private Long yearStart;
    private String monthEnd;
    private Long yearEnd;
}
