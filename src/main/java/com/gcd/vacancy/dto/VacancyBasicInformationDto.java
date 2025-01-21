package com.gcd.vacancy.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VacancyBasicInformationDto {

    private Long id;
    private String title;
    private String name_enterprise;
    private String level;
    private String status;
    private String remuneration;
    private String modality;
    private String description;
    private String location;
    private Long enterpriseId;
    private LocalDateTime createdAt;
}
