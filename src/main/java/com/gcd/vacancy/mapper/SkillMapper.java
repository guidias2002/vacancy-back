package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.SkillDto;
import com.gcd.vacancy.dto.SkillPostDto;
import com.gcd.vacancy.entity.SkillEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    SkillEntity toSkillEntity(String skill);

    List<SkillDto> toSkillDtoList(List<SkillEntity> skillEntityList);
}

