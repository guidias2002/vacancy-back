package com.gcd.vacancy.repository;



import com.gcd.vacancy.entity.EnterpriseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnterpriseRepository extends JpaRepository<EnterpriseEntity, Long> {

    boolean existsByCnpj(String cnpj);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);

    @Query("SELECT c FROM EnterpriseEntity c WHERE c.login = :identifier OR c.email = :identifier")
    Optional<EnterpriseEntity> findByLoginOrEmail(String identifier);
}
