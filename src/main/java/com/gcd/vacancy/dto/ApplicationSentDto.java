package com.gcd.vacancy.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class ApplicationSentDto {

    private Long id;
    private Long candidateId;
    private Long vacancyId;
    private String candidateName;
    private String vacancyTitle;
    private String enterpriseName;
    private LocalDateTime createdAt;
}
