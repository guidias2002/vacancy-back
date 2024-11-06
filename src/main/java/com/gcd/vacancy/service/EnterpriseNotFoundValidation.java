package com.gcd.vacancy.service;

import com.gcd.vacancy.entity.EnterpriseEntity;
import com.gcd.vacancy.exceptions.customExceptions.NotFoundException;
import com.gcd.vacancy.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseNotFoundValidation {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    public EnterpriseEntity findEnterpriseById(Long enterpriseId) {
        return enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new NotFoundException("Empresa com id " + enterpriseId + " não encontrada."));
    }
}
