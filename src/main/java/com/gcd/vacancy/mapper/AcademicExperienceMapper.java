package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.AcademicExperiencePostDto;
import com.gcd.vacancy.entity.AcademicExperienceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AcademicExperienceMapper {

    AcademicExperienceEntity toAcaAcademicExperienceEntity(AcademicExperiencePostDto academicExperiencePostDto);
}
