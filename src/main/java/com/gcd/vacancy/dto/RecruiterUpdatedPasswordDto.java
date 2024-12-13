package com.gcd.vacancy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruiterUpdatedPasswordDto {

    @NotBlank(message = "Campo obrigatório.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$",
            message = "A senha deve ter pelo menos uma letra maiúscula e um número")
    private String newPassword;

    @NotBlank(message = "Campo obrigatório.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$",
            message = "A senha deve ter pelo menos uma letra maiúscula e um número")
    private String NewPasswordConfirmed;
}
