package com.gcd.vacancy.repository;

import com.gcd.vacancy.entity.CurriculumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumRepository extends JpaRepository<CurriculumEntity, Long> {
}
