package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.CandidatePostDto;
import com.gcd.vacancy.entity.CandidateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    CandidateEntity toCandidateEntity(CandidatePostDto candidatePostDto);

}
