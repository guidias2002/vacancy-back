package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.CandidateDto;
import com.gcd.vacancy.dto.CandidatePostDto;
import com.gcd.vacancy.dto.CandidateWithApplicationsDto;
import com.gcd.vacancy.entity.CandidateEntity;
import com.gcd.vacancy.exceptions.customExceptions.NotFoundException;
import com.gcd.vacancy.exceptions.customExceptions.ResourceAlreadyExistsException;
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

        ValidationFields(candidatePostDto);
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
                .orElseThrow(() -> new NotFoundException("Candidato com id " + id + " não encontrado."));

        return candidateMapper.toCandidateWithApplicationsDto(candidate);
    }

    private void ValidationFields(CandidatePostDto candidatePostDto) {
        if(candidateRepository.existsByEmail(candidatePostDto.getEmail())) {
            throw new ResourceAlreadyExistsException("O email " + "'" + candidatePostDto.getEmail() + "'" + " já esta em uso.");
        }

        if(candidateRepository.existsByLogin(candidatePostDto.getLogin())) {
            throw new ResourceAlreadyExistsException("O login " + "'" + candidatePostDto.getLogin() + "'" + " já esta em uso.");
        }
    }
}
