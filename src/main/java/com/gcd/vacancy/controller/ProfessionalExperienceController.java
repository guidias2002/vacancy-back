package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.ProfessionalExperienceDto;
import com.gcd.vacancy.dto.ProfessionalExperiencePostDto;
import com.gcd.vacancy.dto.ProfessionalExperienceUpdateDto;
import com.gcd.vacancy.service.ProfessionalExperienceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professionalExperience")
public class ProfessionalExperienceController {

    @Autowired
    private ProfessionalExperienceService professionalExperienceService;

    @PostMapping("/candidateId/{candidateId}")
    public ResponseEntity<Void> saveProfessionalExperience(@PathVariable Long candidateId, @Valid @RequestBody ProfessionalExperiencePostDto professionalExperiencePostDto) {
        professionalExperienceService.saveProfessionalExperience(candidateId, professionalExperiencePostDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{professionalExperienceId}")
    public ResponseEntity<ProfessionalExperienceDto> updateProfessionalExperience(@PathVariable Long professionalExperienceId, @RequestBody ProfessionalExperienceUpdateDto professionalExperienceUpdateDto) {
        ProfessionalExperienceDto professionalExperienceDto = professionalExperienceService.updateProfessionalExperience(professionalExperienceId, professionalExperienceUpdateDto);

        return ResponseEntity.ok(professionalExperienceDto);
    }
}
