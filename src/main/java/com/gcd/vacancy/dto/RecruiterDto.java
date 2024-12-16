package com.gcd.vacancy.dto;

import com.gcd.vacancy.enums.AccountType;
import com.gcd.vacancy.enums.RecruiterInvitationStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecruiterDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Long enterpriseId;
    private AccountType accountType;
    private RecruiterInvitationStatus invitationStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
