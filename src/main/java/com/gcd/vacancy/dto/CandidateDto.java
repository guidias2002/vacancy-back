package com.gcd.vacancy.dto;


import lombok.Data;


import java.time.LocalDateTime;

@Data
public class CandidateDto {
    private Long id;
    private String name;
    private String login;
    private String password;
    private String email;
    private String accountType;
    private LocalDateTime createdAt;

}