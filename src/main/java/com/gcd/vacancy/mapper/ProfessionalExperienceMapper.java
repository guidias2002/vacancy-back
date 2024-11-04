package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.ProfessionalExperienceDto;
import com.gcd.vacancy.dto.ProfessionalExperiencePostDto;
import com.gcd.vacancy.entity.ProfessionalExperienceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfessionalExperienceMapper {

    ProfessionalExperienceEntity toProfessionalExperienceEntity(ProfessionalExperiencePostDto professionalExperiencePostDto);

    ProfessionalExperienceDto toProfessionalExperienceDto(ProfessionalExperienceEntity professionalExperienceEntity);
}
