package com.gcd.vacancy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruiterLoginDto {

    @NotBlank(message = "O campo email é obrigatório.")
    private String email;

    @NotBlank(message = "O campo senha é obrigatório.")
    private String password;
}
