package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.VacancyBasicInformationDto;
import com.gcd.vacancy.dto.VacancyDto;
import com.gcd.vacancy.dto.VacancyPostDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface VacancyService {

    void saveVacancy(Long enterpriseId, VacancyPostDto vacancyPostDto);

    List<VacancyDto> getAllVacancyByStatusActive();

    VacancyDto getVacancyById(Long vacancyId);

    List<VacancyDto> getVacancyByEnterpriseIdAllInformation(Long enterpriseId);

    List<VacancyBasicInformationDto> getVacancyByEnterpriseIdBasicInformation(Long enterpriseId);
}
