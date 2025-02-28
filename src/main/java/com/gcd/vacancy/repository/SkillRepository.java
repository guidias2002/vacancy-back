package com.gcd.vacancy.repository;

import com.gcd.vacancy.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long> {

    Optional<SkillEntity> findBySkillIgnoreCase(String skill);

    List<SkillEntity> findSkillsByCandidateId(Long candidateId);

    Boolean existsBySkillIgnoreCase(String skill);
}
