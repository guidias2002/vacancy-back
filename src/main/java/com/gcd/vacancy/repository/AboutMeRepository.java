package com.gcd.vacancy.repository;

import com.gcd.vacancy.dto.AboutMeDto;
import com.gcd.vacancy.entity.AboutMeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AboutMeRepository extends JpaRepository<AboutMeEntity, Long> {

    Optional<AboutMeEntity> findAboutMeByCandidateId(Long candidateId);

    Boolean existsByCandidateId(Long candidateId);
}
