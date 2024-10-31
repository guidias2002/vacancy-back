package com.gcd.vacancy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "curriculum")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "about_me_id", referencedColumnName = "id")
    private AboutMeEntity aboutMe;


}
