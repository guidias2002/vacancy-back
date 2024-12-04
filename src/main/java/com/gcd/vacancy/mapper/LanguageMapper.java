package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.LanguageDto;
import com.gcd.vacancy.dto.LanguagePostDto;
import com.gcd.vacancy.entity.LanguageEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LanguageMapper {

    LanguageEntity toLanguageEntity(LanguagePostDto languagePostDto);

    LanguageDto toLanguageDto(LanguageEntity languageEntity);

    List<LanguageDto> toLanguageDtoList(List<LanguageEntity> languageEntityList);
}
