package com.gcd.vacancy.repository;

import com.gcd.vacancy.entity.RecruiterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRepository extends JpaRepository<RecruiterEntity, Long> {


}
