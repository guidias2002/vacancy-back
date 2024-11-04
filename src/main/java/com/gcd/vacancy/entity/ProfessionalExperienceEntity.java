package com.gcd.vacancy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "professional_experience")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalExperienceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String enterprise;
    private String position;
    private String period;
    private String description;
    private Long candidateId;
    private Long curriculumId;
}