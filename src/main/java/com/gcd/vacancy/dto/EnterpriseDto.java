package com.gcd.vacancy.dto;

import com.gcd.vacancy.entity.VacancyEntity;
import com.gcd.vacancy.enums.AccountType;
import lombok.Data;

import java.util.List;

@Data
public class EnterpriseDto {
    private Long id;
    private String name;
    private String cnpj;
    private String login;
    private String email;
    private String password;
    private String accountType;
}
