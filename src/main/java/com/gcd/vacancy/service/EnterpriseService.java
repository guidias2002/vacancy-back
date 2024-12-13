package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface EnterpriseService {

    void saveEnterprise(EnterprisePostDto enterprisePostDto);

    EnterpriseDto getEnterpriseById(Long enterpriseId);

    EnterpriseWithListVacanciesDto getVacanciesByEnterprise(Long enterpriseId);

    List<EnterpriseDto> getAllEnterprise();

    Map<String, Object> loginEnterprise(LoginEnterpriseDto loginEnterpriseDto);

    RecruiterDto disableRecruiterAccount(Long enterpriseId, Long recruiterId);
}
