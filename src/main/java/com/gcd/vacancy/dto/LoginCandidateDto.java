package com.gcd.vacancy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginCandidateDto {

    @NotBlank(message = "O campo loginOrEmail é obrigatório.")
    private String loginOrEmail;

    @NotBlank(message = "O campo senha é obrigatório.")
    private String password;

}
