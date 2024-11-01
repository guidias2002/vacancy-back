package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.*;
import com.gcd.vacancy.entity.CandidateEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    CandidateEntity toCandidateEntity(CandidatePostDto candidatePostDto);

    List<CandidateDto> toCandidateListDto(List<CandidateEntity> candidateEntityList);

    CandidateWithCurriculumDto toCandidateWithCurriculumDto(CandidateEntity candidateEntity);

    CandidateWithApplicationsDto toCandidateWithApplicationsDto(CandidateEntity candidateEntity);

    CandidateWithAllInformationDto toCandidateWithAllInformation(CandidateEntity candidateEntity);

}
