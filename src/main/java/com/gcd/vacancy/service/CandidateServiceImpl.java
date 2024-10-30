package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.CandidateDto;
import com.gcd.vacancy.dto.CandidatePostDto;
import com.gcd.vacancy.dto.CandidateWithApplicationsDto;
import com.gcd.vacancy.dto.LoginCandidateDto;
import com.gcd.vacancy.entity.CandidateEntity;
import com.gcd.vacancy.exceptions.customExceptions.IncorrectCredentialsException;
import com.gcd.vacancy.exceptions.customExceptions.NotFoundException;
import com.gcd.vacancy.exceptions.customExceptions.ResourceAlreadyExistsException;
import com.gcd.vacancy.mapper.CandidateMapper;
import com.gcd.vacancy.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateMapper candidateMapper;

    @Autowired
    private TokenService tokenService;

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

    @Override
    public Map<String, Object> loginCandidate(LoginCandidateDto loginCandidateDto) {
        CandidateEntity candidate = candidateRepository.findByLoginOrEmail(loginCandidateDto.getLoginOrEmail())
                .orElseThrow(() -> new NotFoundException("Candidato não encontrado."));


        if(loginCandidateDto.getPassword().equals(candidate.getPassword())) {
            String token = tokenService.generateToken(loginCandidateDto.getLoginOrEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("accountType", candidate.getAccountType());
            response.put("login", loginCandidateDto.getLoginOrEmail());

            return response;
        } else {
            throw new IncorrectCredentialsException("Senha incorreta.");
        }
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
