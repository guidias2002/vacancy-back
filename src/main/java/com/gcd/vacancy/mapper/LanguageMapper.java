package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.LanguagePostDto;
import com.gcd.vacancy.entity.LanguageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LanguageMapper {

    LanguageEntity toLanguageEntity(LanguagePostDto languagePostDto);
}
