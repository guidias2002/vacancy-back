package com.gcd.vacancy.repository;

import com.gcd.vacancy.entity.ProfessionalExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalExperienceRepository extends JpaRepository<ProfessionalExperienceEntity, Long> {
}
