package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.VacancyDto;
import com.gcd.vacancy.dto.VacancyPostDto;
import com.gcd.vacancy.entity.VacancyEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VacancyMapper {

    VacancyEntity toVacancyEntity(VacancyPostDto vacancyPostDto);

    List<VacancyDto> toListVacancyDto(List<VacancyEntity> vacancyEntities);
}