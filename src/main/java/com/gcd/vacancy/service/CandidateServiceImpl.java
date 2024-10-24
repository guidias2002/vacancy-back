package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.CandidatePostDto;
import com.gcd.vacancy.entity.CandidateEntity;
import com.gcd.vacancy.mapper.CandidateMapper;
import com.gcd.vacancy.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateMapper candidateMapper;

    @Override
    public void saveCandidate(CandidatePostDto candidatePostDto) {
        CandidateEntity newCandidate = candidateMapper.toCandidateEntity(candidatePostDto);

        candidateRepository.save(newCandidate);
    }
}
