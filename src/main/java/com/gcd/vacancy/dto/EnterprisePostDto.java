package com.gcd.vacancy.dto;

import com.gcd.vacancy.enums.AccountType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnterprisePostDto {

    @NotBlank(message = "Campo obrigatório.")
    private String name;

    @NotBlank(message = "Campo obrigatório.")
    @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter 14 dígitos numéricos")
    private String cnpj;

    @NotBlank(message = "Campo obrigatório.")
    @Size(min = 6, message = "O login deve ter no mínimo 6 caracteres")
    private String login;

    @NotBlank(message = "Campo obrigatório.")
    @Email(message = "O email deve estar em um formato válido")
    private String email;

    @NotBlank(message = "Campo obrigatório.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$",
            message = "A senha deve ter pelo menos uma letra maiúscula e um número")
    private String password;
    private final AccountType accountType = AccountType.ENTERPRISE;
}
