package com.gcd.vacancy.repository;

import com.gcd.vacancy.entity.AcademicExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicExperienceRepository extends JpaRepository<AcademicExperienceEntity, Long> {
}
