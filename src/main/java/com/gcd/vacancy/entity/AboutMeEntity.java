package com.gcd.vacancy.entity;

import jakarta.persistence.*;
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
    private String fullName;
    private String location;
    private String cellphoneNumber;
    private String linkedin;
    private Long candidateId;
}
