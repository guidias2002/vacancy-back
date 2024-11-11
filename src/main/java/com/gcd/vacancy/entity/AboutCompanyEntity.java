package com.gcd.vacancy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "about_company")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AboutCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String about;
    private String sector;
    private String linkedin;
    private String webSite;
    private Long enterpriseId;

}
