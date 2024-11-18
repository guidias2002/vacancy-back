package com.gcd.vacancy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AcademicExperiencePostDto {

    private Long id;

    @NotBlank(message = "Campo obrigatório.")
    private String course;

    @NotBlank(message = "Campo obrigatório.")
    private String institution;

    @NotBlank(message = "Campo obrigatório.")
    private String level;

    @NotBlank(message = "Campo obrigatório.")
    private String status;

    @NotBlank(message = "Campo obrigatório.")
    private String monthStart;

    @NotNull(message = "Campo obrigatório.")
    private Long yearStart;

    @NotBlank(message = "Campo obrigatório.")
    private String monthEnd;

    @NotNull(message = "Campo obrigatório.")
    private Long yearEnd;
}
