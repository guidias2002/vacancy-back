package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.VacancyPostDto;
import com.gcd.vacancy.entity.EnterpriseEntity;
import com.gcd.vacancy.entity.VacancyEntity;
import com.gcd.vacancy.mapper.VacancyMapper;
import com.gcd.vacancy.repository.EnterpriseRepository;
import com.gcd.vacancy.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacancyServiceImpl implements VacancyService{

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private VacancyMapper vacancyMapper;

    @Override
    public void saveVacancy(VacancyPostDto vacancyPostDto) {
        VacancyEntity newVacancy = vacancyMapper.toVacancyEntity(vacancyPostDto);

        EnterpriseEntity enterprise = enterpriseRepository.findById(vacancyPostDto.getEnterpriseId())
                        .orElseThrow(() -> new IllegalArgumentException(("Enterprise not found.")));

        newVacancy.setName_enterprise(enterprise.getName());
        newVacancy.setEnterpriseId(enterprise.getId());

        vacancyRepository.save(newVacancy);
    }
}
