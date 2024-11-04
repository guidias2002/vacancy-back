package com.gcd.vacancy.dto;

import com.gcd.vacancy.enums.AccountType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CandidatePostDto {

    private Long id;

    @NotBlank(message = "Campo obrigatório.")
    private String name;

    @NotBlank(message = "Campo obrigatório.")
    @Size(min = 6, message = "O login deve ter no mínimo 6 caracteres")
    private String login;

    @NotBlank(message = "Campo obrigatório.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$",
            message = "A senha deve ter pelo menos uma letra maiúscula e um número")
    private String password;

    @NotBlank(message = "Campo obrigatório.")
    @Email(message = "O email deve estar em um formato válido")
    private String email;

    private final AccountType accountType = AccountType.CANDIDATE;
    private LocalDateTime createdAt;
}
