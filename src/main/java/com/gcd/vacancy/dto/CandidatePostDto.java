package com.gcd.vacancy.dto;

import com.gcd.vacancy.enums.AccountType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CandidatePostDto {

    private Long id;
    private String name;
    private String login;
    private String password;
    private String email;
    private final AccountType accountType = AccountType.CANDIDATE;
    private LocalDateTime createdAt;
}
