package com.gcd.vacancy.repository;

import com.gcd.vacancy.entity.AboutCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutCompanyRepository extends JpaRepository<AboutCompanyEntity, Long> {
}
