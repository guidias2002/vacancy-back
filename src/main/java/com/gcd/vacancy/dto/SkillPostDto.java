package com.gcd.vacancy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SkillPostDto {

    @NotBlank(message = "O campo é obrigatório.")
    private String skill;
}
