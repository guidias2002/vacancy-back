package com.gcd.vacancy.dto;

import com.gcd.vacancy.enums.AccountType;
import lombok.Data;

@Data
public class EnterprisePostDto {

    private Long id;
    private String name;
    private String cnpj;
    private String login;
    private String email;
    private String password;
    private final AccountType accountType = AccountType.ENTERPRISE;
}
