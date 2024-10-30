package com.gcd.vacancy.repository;

import com.gcd.vacancy.entity.AboutMeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutMeRepository extends JpaRepository<AboutMeEntity, Long> {
}
