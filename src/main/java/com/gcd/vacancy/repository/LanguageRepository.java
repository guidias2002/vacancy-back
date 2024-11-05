package com.gcd.vacancy.repository;

import com.gcd.vacancy.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@ResponseStatus
public interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {

    Optional<LanguageEntity> findByLanguageIgnoreCase(String language);
}
