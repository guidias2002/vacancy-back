package com.gcd.vacancy.dto;

import com.gcd.vacancy.enums.AccountType;
import lombok.Data;

@Data
public class CandidatePostDto {

    private Long id;
    private String name;
    private String login;
    private String password;
    private String email;
    private final AccountType accountType = AccountType.CANDIDATE;
}
