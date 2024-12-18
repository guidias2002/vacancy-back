package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.*;
import com.gcd.vacancy.entity.RecruiterEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RecruiterService {

    RecruiterEntity saveRecruiter(RecruiterPostDto recruiterPostDto, Long enterpriseId);

    Map<String, Object> loginRecruiter(RecruiterLoginDto recruiterLoginDto);

    List<RecruiterDto> findAllRecruiters();

    List<RecruiterDto> findAllRecruitersByEnterpriseId(Long enterpriseId);

    RecruiterEmailAndPasswordDto updateRecruiterPassword(Long recruiterId, RecruiterUpdatedPasswordDto updatedPasswordDto);

    RecruiterDto findRecruiterById(Long recruiterId);

}
