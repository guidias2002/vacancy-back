package com.gcd.vacancy.dto;

import lombok.Data;

@Data
public class AcademicExperiencePostDto {

    private String course;
    private String institution;
    private String level;
    private Long completion;
}
