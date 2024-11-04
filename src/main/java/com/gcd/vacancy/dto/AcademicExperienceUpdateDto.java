package com.gcd.vacancy.dto;

import lombok.Data;

@Data
public class AcademicExperienceUpdateDto {

    private String course;
    private String institution;
    private String level;
    private Long completion;

}
