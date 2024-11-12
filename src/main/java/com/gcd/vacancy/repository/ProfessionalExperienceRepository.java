package com.gcd.vacancy.repository;

import com.gcd.vacancy.entity.ProfessionalExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionalExperienceRepository extends JpaRepository<ProfessionalExperienceEntity, Long> {

    List<ProfessionalExperienceEntity> findAllByCandidateId(Long candidateId);
}
