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

    @Autowired
    private EnterpriseNotFoundValidation enterpriseNotFoundValidation;

    @Override
    public void saveVacancy(Long enterpriseId, VacancyPostDto vacancyPostDto) {
        VacancyEntity newVacancy = vacancyMapper.toVacancyEntity(vacancyPostDto);

        EnterpriseEntity enterprise = enterpriseNotFoundValidation.findEnterpriseById(enterpriseId);

        newVacancy.setName_enterprise(enterprise.getName());
        newVacancy.setEnterpriseId(enterpriseId);

        vacancyRepository.save(newVacancy);
    }

    @Override
    public List<VacancyDto> getAllVacancy() {

        return vacancyMapper.toListVacancyDto(vacancyRepository.findAll());
    }

    @Override
    public VacancyDto getVacancyById(Long vacancyId) {
        return vacancyMapper.toVacancyDto(vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new NotFoundException("Vaga com id" + vacancyId + " não encontrada")));
    }

    @Override
    public List<VacancyDto> getVacancyByEnterpriseId(Long enterpriseId) {
        enterpriseNotFoundValidation.findEnterpriseById(enterpriseId);

        return vacancyMapper.toListVacancyDto(vacancyRepository.findByEnterpriseId(enterpriseId));
    }


}
