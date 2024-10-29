package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.EnterpriseDto;
import com.gcd.vacancy.dto.EnterprisePostDto;
import com.gcd.vacancy.dto.EnterpriseWithListVacanciesDto;
import com.gcd.vacancy.entity.EnterpriseEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnterpriseMapper {

    EnterpriseEntity toEnterpriseEntity(EnterprisePostDto enterprisePostDto);

    EnterpriseDto toEnterpriseDto(EnterpriseEntity enterpriseEntity);

    EnterpriseWithListVacanciesDto toEnterpriseWithListVacanciesDto(EnterpriseEntity enterpriseEntity);

    List<EnterpriseDto> toEnterpriseDtoList(List<EnterpriseEntity> enterpriseEntityList);
}
