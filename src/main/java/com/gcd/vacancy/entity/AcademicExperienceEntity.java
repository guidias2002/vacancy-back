package com.gcd.vacancy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "academic_experience")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicExperienceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String course;
    private String institution;
    private String level;
    private String status;
    private String monthStart;
    private Long yearStart;
    private String monthEnd;
    private Long yearEnd;
    private Long candidateId;
    private Long curriculumId;
}
