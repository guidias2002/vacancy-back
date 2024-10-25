package com.gcd.vacancy.controller;

import com.gcd.vacancy.entity.CandidacyEntity;
import com.gcd.vacancy.service.CandidacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidacy")
public class CandidacyController {

    @Autowired
    private CandidacyService candidacyService;

    @PostMapping
    public ResponseEntity<CandidacyEntity> applyToVacancy(@RequestParam Long vacancyId, @RequestParam Long candidateId) {
        CandidacyEntity candidacy = candidacyService.applyToVacancy(vacancyId, candidateId);

        return ResponseEntity.ok(candidacy);
    }
}
