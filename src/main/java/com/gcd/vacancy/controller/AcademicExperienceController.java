package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.AcademicExperienceDto;
import com.gcd.vacancy.dto.AcademicExperiencePostDto;
import com.gcd.vacancy.dto.AcademicExperienceUpdateDto;
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

    @PutMapping("/updateAcademicExperience/{academicExperienceId}")
    public ResponseEntity<AcademicExperienceDto> updateAcademicExperience(@PathVariable Long academicExperienceId, @RequestBody AcademicExperienceUpdateDto academicExperienceUpdateDto) {
        AcademicExperienceDto academicExperienceDto = academicExperienceService.updateAcademicExperience(academicExperienceId, academicExperienceUpdateDto);

        return ResponseEntity.ok(academicExperienceDto);
    }

    @DeleteMapping("/deleteAcademicExperience/{academicExperienceId}")
    public ResponseEntity<Void> deleteAcademicExperienceById(@PathVariable Long academicExperienceId) {
        academicExperienceService.deleteAcademicExperienceById(academicExperienceId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
