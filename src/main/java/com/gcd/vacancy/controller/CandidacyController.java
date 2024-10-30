package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.ApplicationSentDto;
import com.gcd.vacancy.dto.CandidacyDto;
import com.gcd.vacancy.service.CandidacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidacy")
public class CandidacyController {

    @Autowired
    private CandidacyService candidacyService;

    @PostMapping("/vacancy/{vacancyId}/candidate/{candidateId}")
    public ResponseEntity<ApplicationSentDto> applyToVacancy(@PathVariable Long vacancyId, @PathVariable Long candidateId) {
        ApplicationSentDto candidacy = candidacyService.applyToVacancy(vacancyId, candidateId);

        return ResponseEntity.ok(candidacy);
    }

    @GetMapping("/enterpriseId/{enterpriseId}")
    public ResponseEntity<List<CandidacyDto>> getVacancyByEnterprise(@PathVariable Long enterpriseId) {
        List<CandidacyDto> candidacyDtoList = candidacyService.getCandidacyByEnterpriseId(enterpriseId);

        return ResponseEntity.ok(candidacyDtoList);
    }

    @GetMapping("/candidateId/{candidateId}")
    public ResponseEntity<List<CandidacyDto>> getVacancyByCandidate(@PathVariable Long candidateId) {
        List<CandidacyDto> candidacyDtoList = candidacyService.getCandidacyByCandidateId(candidateId);

        return ResponseEntity.ok(candidacyDtoList);
    }
}
