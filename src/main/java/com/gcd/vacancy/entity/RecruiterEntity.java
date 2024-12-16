package com.gcd.vacancy.entity;

import com.gcd.vacancy.enums.AccountType;
import com.gcd.vacancy.enums.RecruiterInvitationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "recruiter")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruiterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @JoinColumn(name = "enterprise_id")
    private Long enterpriseId;

    @Enumerated(EnumType.STRING)
    private final AccountType accountType = AccountType.RECRUITER;

    @Enumerated(EnumType.STRING)
    private RecruiterInvitationStatus invitationStatus;

    @CreationTimestamp
    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;
}
