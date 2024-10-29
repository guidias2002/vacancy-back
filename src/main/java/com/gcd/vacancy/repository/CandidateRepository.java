package com.gcd.vacancy.repository;

import com.gcd.vacancy.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {

    boolean existsByEmail(String email);
    boolean existsByLogin(String login);
}
