package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.AboutCompanyPostDto;
import com.gcd.vacancy.entity.AboutCompanyEntity;
import com.gcd.vacancy.entity.EnterpriseEntity;
import com.gcd.vacancy.mapper.AboutCompanyMapper;
import com.gcd.vacancy.repository.AboutCompanyRepository;
import com.gcd.vacancy.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutCompanyServiceImpl implements AboutCompanyService{

    @Autowired
    private AboutCompanyRepository aboutCompanyRepository;

    @Autowired
    private EnterpriseNotFoundValidation enterpriseNotFoundValidation;

    @Autowired
    private AboutCompanyMapper aboutCompanyMapper;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Override
    public void saveAboutCompany(Long enterpriseId, AboutCompanyPostDto aboutCompanyPostDto) {
        EnterpriseEntity enterpriseEntity = enterpriseNotFoundValidation.findEnterpriseById(enterpriseId);

        AboutCompanyEntity aboutCompany = aboutCompanyMapper.toAboutCompanyEntity(aboutCompanyPostDto);
        aboutCompany.setEnterpriseId(enterpriseId);
        aboutCompanyRepository.save(aboutCompany);

        enterpriseEntity.setAboutCompany(aboutCompany);
        enterpriseRepository.save(enterpriseEntity);
    }
}
