package com.gcd.vacancy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LanguageUpdateDto {

    @NotBlank(message = "Campo obrigatório")
    private String language;

    @NotBlank(message = "Campo obrigatório")
    private String level;
}
