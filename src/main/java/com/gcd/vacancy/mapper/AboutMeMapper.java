package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.AboutMePostDto;
import com.gcd.vacancy.entity.AboutMeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AboutMeMapper {

    AboutMeEntity toAboutMeEntity(AboutMePostDto aboutMePostDto);
}
