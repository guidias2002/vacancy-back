package com.gcd.vacancy.dto;

import com.gcd.vacancy.entity.VacancyEntity;
import lombok.Data;

import java.util.List;

@Data
public class EnterpriseWithListVacanciesDto {
    private Long id;
    private String name;
    private String cnpj;
    private String login;
    private String email;
    private String password;
    private String accountType;
    private List<VacancyEntity> vacancyEntityList;
}
