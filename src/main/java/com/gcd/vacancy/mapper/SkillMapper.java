package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.SkillPostDto;
import com.gcd.vacancy.entity.SkillEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    SkillEntity toSkillEntity(SkillPostDto skillPostDto);
}
