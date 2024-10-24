package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.CandidatePostDto;
import org.springframework.stereotype.Service;

@Service
public interface CandidateService {
    void saveCandidate(CandidatePostDto candidatePostDto);

}
