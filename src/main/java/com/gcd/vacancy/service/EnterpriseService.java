package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.EnterpriseDto;
import com.gcd.vacancy.dto.EnterprisePostDto;
import com.gcd.vacancy.dto.EnterpriseWithListVacanciesDto;
import com.gcd.vacancy.dto.VacancyDto;
import com.gcd.vacancy.entity.EnterpriseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface EnterpriseService {

    void saveEnterprise(EnterprisePostDto enterprisePostDto);

    EnterpriseDto getEnterpriseById(Long enterpriseId);

    EnterpriseWithListVacanciesDto getVacanciesByEnterprise(Long enterpriseId);

    List<EnterpriseDto> getAllEnterprise();
}
