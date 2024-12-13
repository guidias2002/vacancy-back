package com.gcd.vacancy.repository;

import com.gcd.vacancy.entity.RecruiterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruiterRepository extends JpaRepository<RecruiterEntity, Long> {

    Optional<RecruiterEntity> findRecruiterByEmail(String email);

    List<RecruiterEntity> findAllRecruiterByEnterpriseId(Long enterpriseId);
}
