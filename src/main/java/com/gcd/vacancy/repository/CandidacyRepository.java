package com.gcd.vacancy.repository;

import com.gcd.vacancy.entity.CandidacyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidacyRepository extends JpaRepository<CandidacyEntity, Long> {

    boolean existsByCandidateIdAndVacancyId(Long candidateId, Long vacancyId);

    List<CandidacyEntity> findByEnterpriseId(Long enterpriseId);

    List<CandidacyEntity> findByCandidateId(Long candidateId);
}
