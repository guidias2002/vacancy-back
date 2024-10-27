package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.EnterpriseDto;
import com.gcd.vacancy.dto.VacancyDto;
import com.gcd.vacancy.dto.VacancyPostDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface VacancyService {

    void saveVacancy(VacancyPostDto vacancyPostDto);


}
