package com.gcd.vacancy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "curriculum")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "about_me_id", referencedColumnName = "id")
    private AboutMeEntity aboutMe;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "academic_experience_id", referencedColumnName = "id")
    private List<AcademicExperienceEntity> academicExperienceList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "professional_experience_id", referencedColumnName = "id")
    private List<ProfessionalExperienceEntity> professionalExperienceList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private List<LanguageEntity> languageList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    private List<SkillEntity> skillList = new ArrayList<>();

}
