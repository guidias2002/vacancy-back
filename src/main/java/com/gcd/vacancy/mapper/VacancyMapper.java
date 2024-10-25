package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.VacancyPostDto;
import com.gcd.vacancy.entity.VacancyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VacancyMapper {

    @Mapping(target = "enterprise", ignore = true)
    VacancyEntity toVacancyEntity(VacancyPostDto vacancyPostDto);
}
