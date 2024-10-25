package com.gcd.vacancy.repository;

import com.gcd.vacancy.entity.CandidacyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidacyRepository extends JpaRepository<CandidacyEntity, Long> {
}
