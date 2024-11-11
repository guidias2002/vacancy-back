package com.gcd.vacancy.dto;

import lombok.Data;

@Data
public class AboutCompanyDto {

    private Long id;
    private String about;
    private String sector;
    private String linkedin;
    private String webSite;
    private Long enterpriseId;
}
