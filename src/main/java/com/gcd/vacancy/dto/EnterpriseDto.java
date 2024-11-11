package com.gcd.vacancy.dto;


import lombok.Data;

import java.time.LocalDateTime;


@Data
public class EnterpriseDto {
    private Long id;
    private String name;
    private String cnpj;
    private String login;
    private String email;
    private String password;
    private String accountType;
    private LocalDateTime createdAt;
    private AboutCompanyDto aboutCompany;
}
