package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.VacancyDto;
import com.gcd.vacancy.dto.VacancyPostDto;
import com.gcd.vacancy.entity.EnterpriseEntity;
import com.gcd.vacancy.entity.VacancyEntity;
import com.gcd.vacancy.exceptions.customExceptions.NotFoundException;
import com.gcd.vacancy.mapper.VacancyMapper;
import com.gcd.vacancy.repository.EnterpriseRepository;
import com.gcd.vacancy.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VacancyServiceImpl implements VacancyService{

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private VacancyMapper vacancyMapper;

    @Override
    public void saveVacancy(Long enterpriseId, VacancyPostDto vacancyPostDto) {
        VacancyEntity newVacancy = vacancyMapper.toVacancyEntity(vacancyPostDto);

        EnterpriseEntity enterprise = enterpriseRepository.findById(enterpriseId)
                        .orElseThrow(() -> new NotFoundException("Empresa com id " + enterpriseId + " não encontrada."));

        newVacancy.setName_enterprise(enterprise.getName());
        newVacancy.setEnterpriseId(enterpriseId);

        vacancyRepository.save(newVacancy);
    }

    @Override
    public List<VacancyDto> getAllVacancy() {
        List<VacancyDto> vacancyDtoList = vacancyMapper.toListVacancyDto(vacancyRepository.findAll());

        return vacancyDtoList;
    }


}
