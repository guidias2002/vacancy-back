package com.gcd.vacancy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProfessionalExperiencePostDto {

    @NotBlank(message = "Campo obrigatório.")
    private String enterprise;

    @NotBlank(message = "Campo obrigatório.")
    private String position;

    @NotBlank(message = "Campo obrigatório.")
    private String period;

    @NotBlank(message = "Campo obrigatório.")
    private String description;
}
