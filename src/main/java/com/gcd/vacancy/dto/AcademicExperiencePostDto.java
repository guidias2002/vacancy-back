package com.gcd.vacancy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AcademicExperiencePostDto {

    @NotBlank(message = "Campo obrigatório.")
    private String course;

    @NotBlank(message = "Campo obrigatório.")
    private String institution;

    @NotBlank(message = "Campo obrigatório.")
    private String level;

    @NotBlank(message = "Campo obrigatório.")
    private Long completion;
}
