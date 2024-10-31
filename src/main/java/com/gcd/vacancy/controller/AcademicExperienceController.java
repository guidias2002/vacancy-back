package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.AcademicExperiencePostDto;
import com.gcd.vacancy.service.AcademicExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academicExperience")
public class AcademicExperienceController {

    @Autowired
    private AcademicExperienceService academicExperienceService;

    @PostMapping("/candidateId/{candidateId}")
    public ResponseEntity<Void> sendAcademicExperience(@PathVariable Long candidateId, @RequestBody AcademicExperiencePostDto academicExperiencePostDto) {
        academicExperienceService.saveAcademicExperience(candidateId, academicExperiencePostDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
