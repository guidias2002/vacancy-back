package com.gcd.vacancy.repository;

import com.gcd.vacancy.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {

    boolean existsByEmail(String email);
    boolean existsByLogin(String login);

    @Query("SELECT c FROM CandidateEntity c WHERE c.login = :identifier OR c.email = :identifier")
    Optional<CandidateEntity> findByLoginOrEmail(String identifier);
}
