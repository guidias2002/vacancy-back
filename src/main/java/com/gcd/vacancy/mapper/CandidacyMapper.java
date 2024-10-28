package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.ApplicationSentDto;
import com.gcd.vacancy.entity.CandidacyEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CandidacyMapper {

    ApplicationSentDto toApplicationSentDto(CandidacyEntity candidacyEntity);


}
