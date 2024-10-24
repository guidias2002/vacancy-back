package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.EnterprisePostDto;
import org.springframework.stereotype.Service;

@Service
public interface EnterpriseService {

    void saveEnterprise(EnterprisePostDto enterprisePostDto);
}
