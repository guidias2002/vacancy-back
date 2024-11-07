package com.gcd.vacancy.dto;

import lombok.Data;


@Data
public class VacancyPostDto {

    private Long id;
    private String title;
    private String name_enterprise;
    private String level;
    private String remuneration;
    private String modality;
    private String description;
    private String location;
}
