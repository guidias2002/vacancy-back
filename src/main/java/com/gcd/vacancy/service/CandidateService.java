package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.CandidateDto;
import com.gcd.vacancy.dto.CandidatePostDto;
import com.gcd.vacancy.dto.CandidateWithApplicationsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidateService {
    void saveCandidate(CandidatePostDto candidatePostDto);

    List<CandidateDto> findAllCandidates();

    CandidateWithApplicationsDto findCandidate(Long id);

}
