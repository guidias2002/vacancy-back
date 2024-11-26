package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.AcademicExperienceDto;
import com.gcd.vacancy.dto.AcademicExperiencePostDto;
import com.gcd.vacancy.entity.AcademicExperienceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AcademicExperienceMapper {

    AcademicExperienceEntity toAcaAcademicExperienceEntity(AcademicExperiencePostDto academicExperiencePostDto);

    AcademicExperienceDto toAcademicExperienceDto(AcademicExperienceEntity academicExperienceEntity);
    List<AcademicExperienceDto> toAcademicExperienceDtoList(List<AcademicExperienceEntity> academicExperienceEntity);
}
