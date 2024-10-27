package com.gcd.vacancy.repository;

import com.gcd.vacancy.entity.VacancyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<VacancyEntity, Long> {

    List<VacancyEntity> findByEnterpriseId(Long enterpriseId);
}
