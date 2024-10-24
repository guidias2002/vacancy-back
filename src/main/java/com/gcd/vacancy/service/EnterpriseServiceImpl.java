package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.EnterprisePostDto;
import com.gcd.vacancy.entity.EnterpriseEntity;
import com.gcd.vacancy.mapper.EnterpriseMapper;
import com.gcd.vacancy.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Override
    public void saveEnterprise(EnterprisePostDto enterprisePostDto) {
        EnterpriseEntity newEterprise = enterpriseMapper.toEnterpriseEntity(enterprisePostDto);

        enterpriseRepository.save(newEterprise);
    }
}
