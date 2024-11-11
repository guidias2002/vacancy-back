package com.gcd.vacancy.dto;

import lombok.Data;

import java.util.List;


@Data
public class VacancyPostDto {

    private String title;
    private String level;
    private String remuneration;
    private String modality;
    private String description;
    private String location;
    private List<String> responsibilities;
    private List<String> requirements;
    private List<String> additionalInformation;
}
