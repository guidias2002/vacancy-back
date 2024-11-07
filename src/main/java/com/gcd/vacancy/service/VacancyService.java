package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.VacancyDto;
import com.gcd.vacancy.dto.VacancyPostDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface VacancyService {

    void saveVacancy(Long enterpriseId, VacancyPostDto vacancyPostDto);

    List<VacancyDto> getAllVacancy();

    VacancyDto getVacancyById(Long vacancyId);

    List<VacancyDto> getVacancyByEnterpriseId(Long id);
}
