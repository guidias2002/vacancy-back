package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.EnterpriseDto;
import com.gcd.vacancy.dto.EnterprisePostDto;
import com.gcd.vacancy.dto.EnterpriseWithListVacanciesDto;
import com.gcd.vacancy.dto.VacancyDto;
import com.gcd.vacancy.entity.EnterpriseEntity;
import com.gcd.vacancy.entity.VacancyEntity;
import com.gcd.vacancy.mapper.EnterpriseMapper;
import com.gcd.vacancy.mapper.VacancyMapper;
import com.gcd.vacancy.repository.EnterpriseRepository;
import com.gcd.vacancy.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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

    @Override
    public void saveEnterprise(EnterprisePostDto enterprisePostDto) {
        EnterpriseEntity newEnterprise = enterpriseMapper.toEnterpriseEntity(enterprisePostDto);

        enterpriseRepository.save(newEnterprise);
    }

    @Override
    public EnterpriseDto getEnterpriseEntity(Long enterpriseId) {
        EnterpriseEntity enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new IllegalArgumentException("Enterprise not found."));

        return enterpriseMapper.toEnterpriseDto(enterprise);
    }

    @Override
    public EnterpriseWithListVacanciesDto getVacanciesByEnterprise(Long enterpriseId) {
        EnterpriseEntity enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new IllegalArgumentException("Enterprise not found."));

        return enterpriseMapper.toEnterpriseWithListVacanciesDto(enterprise);
    }


}
