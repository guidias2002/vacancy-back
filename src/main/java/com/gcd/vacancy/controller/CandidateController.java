package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.CandidatePostDto;
import com.gcd.vacancy.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
