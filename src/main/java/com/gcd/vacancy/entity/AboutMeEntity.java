package com.gcd.vacancy.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "about_me")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AboutMeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String fullName;

    @NotBlank(message = "Campo obrigatório")
    private String location;

    @NotBlank(message = "Campo obrigatório")
    private String cellphoneNumber;

    @NotBlank(message = "Campo obrigatório")
    private String linkedin;

    private Long candidateId;
    private Long curriculumId;
}
