package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.AboutCompanyPostDto;
import com.gcd.vacancy.entity.AboutCompanyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AboutCompanyMapper {

    AboutCompanyEntity toAboutCompanyEntity(AboutCompanyPostDto aboutCompanyPostDto);

}
