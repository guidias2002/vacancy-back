package com.gcd.vacancy.dto;

import lombok.Data;

@Data
public class ApplicationSentDto {

    private Long id;
    private Long candidate;
    private Long vacancy;
    private String candidateName;
    private String vacancyName;
    private String enterpriseName;
}
