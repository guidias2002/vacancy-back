package com.gcd.vacancy.repository;

import com.gcd.vacancy.entity.VacancyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepository extends JpaRepository<VacancyEntity, Long> {
}
