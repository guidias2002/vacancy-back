package com.gcd.vacancy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AcademicExperiencePostDto {

    @NotBlank(message = "Campo obrigatório.")
    private String course;

    @NotBlank(message = "Campo obrigatório.")
    private String institution;

    @NotBlank(message = "Campo obrigatório.")
    private String level;

    @NotNull(message = "Campo obrigatório.")
    private Long completion;
}
