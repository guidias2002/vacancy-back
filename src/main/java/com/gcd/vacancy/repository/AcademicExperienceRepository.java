package com.gcd.vacancy.repository;

import com.gcd.vacancy.dto.AcademicExperienceDto;
import com.gcd.vacancy.entity.AcademicExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicExperienceRepository extends JpaRepository<AcademicExperienceEntity, Long> {
    List<AcademicExperienceEntity> findAllByCandidateId(Long candidateId);
}

