package com.gcd.vacancy.dto;

import lombok.Data;

@Data
public class AcademicExperienceDto {

    private Long id;
    private String course;
    private String institution;
    private String level;
    private Long completion;
}
