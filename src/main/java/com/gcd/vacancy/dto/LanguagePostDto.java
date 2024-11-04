package com.gcd.vacancy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LanguagePostDto {

    @NotBlank(message = "O campo é obrigatório.")
    private String language;

    @NotBlank(message = "O campo é obrigatório.")
    private String level;
}
