package com.gcd.vacancy.entity;

import com.gcd.vacancy.enums.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "O CNPJ é obrigatório")
    @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter 14 dígitos numéricos")
    @Column(nullable = false, unique = true)
    private String cnpj;

    @NotBlank(message = "O login é obrigatório")
    @Size(min = 6, message = "O login deve ter no mínimo 6 caracteres")
    @Column(nullable = false, unique = true)
    private String login;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve estar em um formato válido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$",
            message = "A senha deve ter pelo menos uma letra maiúscula e um número")
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
