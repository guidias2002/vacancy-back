package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.EnterprisePostDto;
import com.gcd.vacancy.dto.VacancyPostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EnterpriseService {

    void saveEnterprise(EnterprisePostDto enterprisePostDto);

}
