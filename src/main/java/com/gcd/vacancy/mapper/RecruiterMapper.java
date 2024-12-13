package com.gcd.vacancy.mapper;

import com.gcd.vacancy.dto.RecruiterDto;
import com.gcd.vacancy.dto.RecruiterEmailAndPasswordDto;
import com.gcd.vacancy.entity.RecruiterEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecruiterMapper {

    RecruiterDto toRecruiterDto(RecruiterEntity recruiterEntity);

    List<RecruiterDto> toRecruiterDtoList(List<RecruiterEntity> recruiterEntityList);

    RecruiterEmailAndPasswordDto toRecruiterEmailAndPasswordDto(RecruiterEntity recruiterEntity);
}
