package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.EnterpriseDto;
import com.gcd.vacancy.dto.EnterprisePostDto;
import com.gcd.vacancy.dto.EnterpriseWithListVacanciesDto;
import com.gcd.vacancy.dto.LoginEnterpriseDto;
import com.gcd.vacancy.entity.EnterpriseEntity;
import com.gcd.vacancy.exceptions.customExceptions.IncorrectCredentialsException;
import com.gcd.vacancy.exceptions.customExceptions.NotFoundException;
import com.gcd.vacancy.exceptions.customExceptions.ResourceAlreadyExistsException;
import com.gcd.vacancy.mapper.EnterpriseMapper;
import com.gcd.vacancy.mapper.VacancyMapper;
import com.gcd.vacancy.repository.EnterpriseRepository;
import com.gcd.vacancy.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void saveEnterprise(EnterprisePostDto enterprisePostDto) {
        ValidationFields(enterprisePostDto);
        EnterpriseEntity newEnterprise = enterpriseMapper.toEnterpriseEntity(enterprisePostDto);

        enterpriseRepository.save(newEnterprise);
    }

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
        List<EnterpriseDto> enterpriseDtoList = enterpriseMapper.toEnterpriseDtoList(enterpriseRepository.findAll());

        return enterpriseDtoList;
    }

    @Override
    public Map<String, Object> loginEnterprise(LoginEnterpriseDto loginEnterpriseDto) {
        EnterpriseEntity enterprise = enterpriseRepository.findByLoginOrEmail(loginEnterpriseDto.getLoginOrEmail())
                .orElseThrow(() -> new NotFoundException("Empresa não encontrada."));

        if(loginEnterpriseDto.getPassword().equals(enterprise.getPassword())) {
            String token = tokenService.generateToken(loginEnterpriseDto.getLoginOrEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("accountType", enterprise.getAccountType());
            response.put("login", loginEnterpriseDto.getLoginOrEmail());

            return response;
        } else {
            throw new IncorrectCredentialsException("Senha incorreta.");
        }
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
