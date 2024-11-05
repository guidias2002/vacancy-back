package com.gcd.vacancy.dto;

import lombok.Data;

import java.util.List;

@Data
public class CurriculumDto {

    private Long id;
    private AboutMeDto aboutMe;
    private List<AcademicExperienceDto> academicExperienceList;
    private List<ProfessionalExperienceDto> professionalExperienceList;
    private List<LanguageDto> languageList;
    private List<SkillDto> skillList;
}
