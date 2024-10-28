package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.CandidateDto;
import com.gcd.vacancy.dto.CandidatePostDto;
import com.gcd.vacancy.dto.CandidateWithApplicationsDto;
import com.gcd.vacancy.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping
    public ResponseEntity sendCandidate(@RequestBody CandidatePostDto candidatePostDto) {
        candidateService.saveCandidate(candidatePostDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CandidateDto>> findAllCandidates() {
        List<CandidateDto> listCandidates = candidateService.findAllCandidates();

        return ResponseEntity.ok(listCandidates);
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<CandidateWithApplicationsDto> findCandiate(@PathVariable Long candidateId) {
        CandidateWithApplicationsDto candidate = candidateService.findCandidate(candidateId);

        return ResponseEntity.ok(candidate);
    }


}
