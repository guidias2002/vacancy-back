package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.*;
import com.gcd.vacancy.entity.EnterpriseEntity;
import com.gcd.vacancy.entity.RecruiterEntity;
import com.gcd.vacancy.enums.RecruiterInvitationStatus;
import com.gcd.vacancy.exceptions.customExceptions.IncorrectCredentialsException;
import com.gcd.vacancy.exceptions.customExceptions.NotFoundException;
import com.gcd.vacancy.exceptions.customExceptions.ResourceAlreadyExistsException;
import com.gcd.vacancy.mapper.EnterpriseMapper;
import com.gcd.vacancy.mapper.RecruiterMapper;
import com.gcd.vacancy.mapper.VacancyMapper;
import com.gcd.vacancy.repository.EnterpriseRepository;
import com.gcd.vacancy.repository.RecruiterRepository;
import com.gcd.vacancy.repository.VacancyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private VacancyMapper vacancyMapper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EnterpriseNotFoundValidation enterpriseNotFoundValidation;

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Autowired
    private RecruiterMapper recruiterMapper;

    @Override
    public void saveEnterprise(EnterprisePostDto enterprisePostDto) {
        ValidationFields(enterprisePostDto);
        EnterpriseEntity newEnterprise = enterpriseMapper.toEnterpriseEntity(enterprisePostDto);

        enterpriseRepository.save(newEnterprise);
    }

    @Transactional
    @Override
    public EnterpriseDto getEnterpriseById(Long enterpriseId) {
        EnterpriseEntity enterprise = enterpriseNotFoundValidation.findEnterpriseById(enterpriseId);

        return enterpriseMapper.toEnterpriseDto(enterprise);
    }

    @Override
    public EnterpriseWithListVacanciesDto getVacanciesByEnterprise(Long enterpriseId) {
        EnterpriseEntity enterprise = enterpriseNotFoundValidation.findEnterpriseById(enterpriseId);

        return enterpriseMapper.toEnterpriseWithListVacanciesDto(enterprise);
    }

    @Override
    public List<EnterpriseDto> getAllEnterprise() {
        return enterpriseMapper.toEnterpriseDtoList(enterpriseRepository.findAll());
    }

    @Override
    public Map<String, Object> loginEnterprise(LoginEnterpriseDto loginEnterpriseDto) {
        EnterpriseEntity enterprise = enterpriseRepository.findByLoginOrEmail(loginEnterpriseDto.getLoginOrEmail())
                .orElseThrow(() -> new NotFoundException("Empresa não encontrada."));

        if(loginEnterpriseDto.getPassword().equals(enterprise.getPassword())) {
            String token = tokenService.generateToken(loginEnterpriseDto.getLoginOrEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("userName", enterprise.getName());
            response.put("userId", enterprise.getId());
            response.put("accountType", enterprise.getAccountType());
            response.put("login", loginEnterpriseDto.getLoginOrEmail());

            return response;
        } else {
            throw new IncorrectCredentialsException("Senha incorreta.");
        }
    }

    @Override
    public RecruiterDto disableRecruiterAccount(Long enterpriseId, Long recruiterId) {
        EnterpriseEntity enterpriseEntity = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new NotFoundException("Empresa com id " + enterpriseId + " não encontrada."));

        RecruiterEntity recruiterEntity = recruiterRepository.findById(recruiterId)
                .orElseThrow(() -> new NotFoundException("Recrutador com id " + recruiterId + " não encontrado."));

        if(!recruiterEntity.getEnterpriseId().equals(enterpriseId)) {
            throw new NotFoundException("O usuário '" + recruiterEntity.getEmail() + "' não está na lista de recrutadores da empresa " + enterpriseEntity.getName() + ".");
        }

        if(!recruiterEntity.getInvitationStatus().equals(RecruiterInvitationStatus.INATIVO)) {
            recruiterEntity.setInvitationStatus(RecruiterInvitationStatus.INATIVO);
            recruiterEntity.setUpdatedAt(LocalDateTime.now());
            recruiterRepository.save(recruiterEntity);
        }

        return recruiterMapper.toRecruiterDto(recruiterEntity);
    }

    @Override
    public void enableRecruiterAccount(Long enterpriseId, Long recruiterId) {
        EnterpriseEntity enterpriseEntity = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new NotFoundException("Empresa com id " + enterpriseId + " não encontrada."));

        RecruiterEntity recruiterEntity = recruiterRepository.findById(recruiterId)
                .orElseThrow(() -> new NotFoundException("Recrutador com id " + recruiterId + " não encontrado."));

        if(!recruiterEntity.getEnterpriseId().equals(enterpriseId)) {
            throw new NotFoundException("O usuário '" + recruiterEntity.getEmail() + "' não está na lista de recrutadores da empresa " + enterpriseEntity.getName() + ".");
        }

        recruiterEntity.setInvitationStatus(RecruiterInvitationStatus.ATIVO);
        recruiterEntity.setUpdatedAt(LocalDateTime.now());
        recruiterRepository.save(recruiterEntity);
    }


    private void ValidationFields(EnterprisePostDto enterprisePostDto) {
        if(enterpriseRepository.existsByEmail(enterprisePostDto.getEmail())) {
            throw new ResourceAlreadyExistsException("O email " + "'" + enterprisePostDto.getEmail() + "'" + " já esta em uso.");
        }

        if(enterpriseRepository.existsByCnpj(enterprisePostDto.getCnpj())) {
            throw new ResourceAlreadyExistsException("O CNPJ " + "'" + enterprisePostDto.getCnpj() + "'" + " já esta em uso.");
        }

        if(enterpriseRepository.existsByLogin(enterprisePostDto.getLogin())) {
            throw new ResourceAlreadyExistsException("O login " + "'" + enterprisePostDto.getLogin() + "'" + " já esta em uso.");
        }
    }


}
