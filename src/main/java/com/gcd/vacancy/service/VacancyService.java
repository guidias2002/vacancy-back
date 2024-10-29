package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.VacancyPostDto;
import org.springframework.stereotype.Service;


@Service
public interface VacancyService {

    void saveVacancy(Long enterpriseId, VacancyPostDto vacancyPostDto);


}
