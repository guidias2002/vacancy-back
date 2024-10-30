package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.CandidateDto;
import com.gcd.vacancy.dto.CandidatePostDto;
import com.gcd.vacancy.dto.CandidateWithApplicationsDto;
import com.gcd.vacancy.dto.LoginCandidateDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CandidateService {
    void saveCandidate(CandidatePostDto candidatePostDto);

    List<CandidateDto> findAllCandidates();

    CandidateWithApplicationsDto findCandidate(Long id);

    Map<String, Object> loginCandidate(LoginCandidateDto loginCandidateDto);

}
