package com.gcd.vacancy.entity;

import com.gcd.vacancy.enums.VacancyStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vacancy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacancyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String name_enterprise;

    @Column(nullable = false)
    private String level;

    @Column(nullable = false)
    private String remuneration;

    @Column(nullable = false)
    private String modality;

    @Enumerated(EnumType.STRING)
    private VacancyStatus status;

    @Lob
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private List<String> responsibilities;

    @Column(nullable = false)
    private List<String> requirements;

    @Column(nullable = false)
    private List<String> additionalInformation;

    @Column(nullable = false)
    private String location;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @JoinColumn(name = "enterprise_id")
    private Long enterpriseId;


}
