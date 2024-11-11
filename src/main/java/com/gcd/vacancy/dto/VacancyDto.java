package com.gcd.vacancy.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class VacancyDto {

    private Long id;
    private String title;
    private String name_enterprise;
    private String level;
    private String remuneration;
    private String modality;
    private String description;
    private String location;
    private Long enterpriseId;
    private LocalDateTime createdAt;
    private List<String> responsibilities;
    private List<String> requirements;
    private List<String> additionalInformation;

}
