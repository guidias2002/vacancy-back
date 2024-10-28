package com.gcd.vacancy.entity;

import com.gcd.vacancy.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "enterprise")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private final AccountType accountType = AccountType.ENTERPRISE;

    @CreationTimestamp
    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "enterpriseId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VacancyEntity> vacancyEntityList;


}
