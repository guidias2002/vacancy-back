package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.CandidateDto;
import com.gcd.vacancy.dto.CandidatePostDto;
import com.gcd.vacancy.dto.CandidateWithApplicationsDto;
import com.gcd.vacancy.entity.CandidateEntity;
import com.gcd.vacancy.mapper.CandidateMapper;
import com.gcd.vacancy.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<CandidateDto> findAllCandidates() {
        List<CandidateDto> listCandidates = candidateMapper.toCandidateListDto(candidateRepository.findAll());

        return listCandidates;
    }

    @Override
    public CandidateWithApplicationsDto findCandidate(Long id) {
        CandidateEntity candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found"));

        return candidateMapper.toCandidateWithApplicationsDto(candidate);
    }
}
