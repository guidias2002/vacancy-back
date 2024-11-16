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

import java.util.List;

@RestController
@RequestMapping("/professionalExperience")
public class ProfessionalExperienceController {

    @Autowired
    private ProfessionalExperienceService professionalExperienceService;

    @PostMapping("/candidateId/{candidateId}")
    public ResponseEntity<Void> saveProfessionalExperiences(@PathVariable Long candidateId, @Valid @RequestBody List<ProfessionalExperiencePostDto> professionalExperiencePostDtos) {

        professionalExperienceService.saveProfessionalExperience(candidateId, professionalExperiencePostDtos);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{professionalExperienceId}")
    public ResponseEntity<ProfessionalExperienceDto> updateProfessionalExperience(@PathVariable Long professionalExperienceId, @RequestBody ProfessionalExperienceUpdateDto professionalExperienceUpdateDto) {
        ProfessionalExperienceDto professionalExperienceDto = professionalExperienceService.updateProfessionalExperience(professionalExperienceId, professionalExperienceUpdateDto);

        return ResponseEntity.ok(professionalExperienceDto);
    }

    @DeleteMapping("/delete/{professionalExperienceId}")
    public ResponseEntity<Void> deleteProfessionalExperience(@PathVariable Long professionalExperienceId) {
        professionalExperienceService.deleteProfessionalExperience(professionalExperienceId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAllByCandidateId/{candidateId}")
    public ResponseEntity<List<ProfessionalExperienceDto>> findProfessionalExperienceByCandidate(@PathVariable Long candidateId) {
        List<ProfessionalExperienceDto> professionalExperienceListDto = professionalExperienceService.findProfessionalExperienceListByCandidate(candidateId);

        return ResponseEntity.ok(professionalExperienceListDto);
    }

    @PutMapping("/saveOrUpdateExperience/{candidateId}")
    public ResponseEntity<Void> saveOrUpdateProfessionalExperiences(@PathVariable Long candidateId, @RequestBody List<ProfessionalExperiencePostDto> professionalExperiencePostDtos) {
        professionalExperienceService.saveOrUpdateProfessionalExperiences(candidateId, professionalExperiencePostDtos);

        return ResponseEntity.ok().build();
    }
}
