package com.gcd.vacancy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProfessionalExperiencePostDto {

    private Long id;

    @NotBlank(message = "Campo obrigatório.")
    private String enterprise;

    @NotBlank(message = "Campo obrigatório.")
    private String position;

    @NotBlank(message = "Campo obrigatório.")
    private String monthStart;

    @NotBlank(message = "Campo obrigatório.")
    private Long yearStart;

    private String monthEnd;

    private Long yearEnd;

    private Boolean isCurrentJob;

    @NotBlank(message = "Campo obrigatório.")
    private String description;
}
