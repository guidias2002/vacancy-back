package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.AboutCompanyPostDto;
import org.springframework.stereotype.Service;

@Service
public interface AboutCompanyService {

    void saveAboutCompany(Long enterpriseId, AboutCompanyPostDto aboutCompanyPostDto);
}
