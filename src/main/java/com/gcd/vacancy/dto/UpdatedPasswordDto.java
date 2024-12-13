package com.gcd.vacancy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatedPasswordDto {

    @NotBlank(message = "Campo obrigatório.")
    private String newPassword;

    @NotBlank(message = "Campo obrigatório.")
    private String NewPasswordConfirmed;
}
