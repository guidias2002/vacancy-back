package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.EnterprisePostDto;
import com.gcd.vacancy.entity.EnterpriseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnterpriseMapper {

    EnterpriseEntity toEnterpriseEntity(EnterprisePostDto enterprisePostDto);
}
