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
}
