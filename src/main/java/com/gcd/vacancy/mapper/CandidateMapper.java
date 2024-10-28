package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.CandidateDto;
import com.gcd.vacancy.dto.CandidatePostDto;
import com.gcd.vacancy.dto.CandidateWithApplicationsDto;
import com.gcd.vacancy.entity.CandidateEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    CandidateEntity toCandidateEntity(CandidatePostDto candidatePostDto);

    List<CandidateDto> toCandidateListDto(List<CandidateEntity> candidateEntityList);

    CandidateWithApplicationsDto toCandidateWithApplicationsDto(CandidateEntity candidateEntity);

}
