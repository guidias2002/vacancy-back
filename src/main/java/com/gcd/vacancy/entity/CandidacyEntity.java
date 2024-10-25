package com.gcd.vacancy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "candidacy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidacyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "candidate_id", nullable = false)
    private Long candidate_id;

    @JoinColumn(name = "vacancy_id", nullable = false)
    private Long vacancy_id;

    private String candidateName;
    private String vacancyTitle;
    private String enterpriseName;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
