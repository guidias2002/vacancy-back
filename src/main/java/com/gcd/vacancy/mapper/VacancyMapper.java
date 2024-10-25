package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.VacancyPostDto;
import com.gcd.vacancy.entity.VacancyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VacancyMapper {

    VacancyEntity toVacancyEntity(VacancyPostDto vacancyPostDto);
}
