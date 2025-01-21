package com.gcd.vacancy.dto;

import com.gcd.vacancy.enums.VacancyStatus;
import lombok.Data;

import java.util.List;


@Data
public class VacancyPostDto {

    private String title;
    private String level;
    private String remuneration;
    private String description;
    private String modality;
    private String status;
    private String location;
    private List<String> responsibilities;
    private List<String> requirements;
    private List<String> additionalInformation;
}
