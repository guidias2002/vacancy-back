package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.RecruiterDto;
import com.gcd.vacancy.dto.RecruiterEmailAndPasswordDto;
import com.gcd.vacancy.dto.RecruiterLoginDto;
import com.gcd.vacancy.dto.UpdatedPasswordDto;
import com.gcd.vacancy.entity.RecruiterEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecruiterService {

    RecruiterEntity saveRecruiter(String email, Long enterpriseId);

    String loginRecruiter(RecruiterLoginDto recruiterLoginDto);

    List<RecruiterDto> findAllRecruiters();

    List<RecruiterDto> findAllRecruitersByEnterpriseId(Long enterpriseId);

    RecruiterEmailAndPasswordDto updateRecruiterPassword(Long recruiterId, UpdatedPasswordDto updatedPasswordDto);

}
