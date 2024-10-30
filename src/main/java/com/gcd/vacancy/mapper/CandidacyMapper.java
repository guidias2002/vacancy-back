package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.ApplicationSentDto;
import com.gcd.vacancy.dto.CandidacyDto;
import com.gcd.vacancy.entity.CandidacyEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CandidacyMapper {

    ApplicationSentDto toApplicationSentDto(CandidacyEntity candidacyEntity);

    List<CandidacyDto> toCandidacyListDto(List<CandidacyEntity> candidacyEntityList);


}
