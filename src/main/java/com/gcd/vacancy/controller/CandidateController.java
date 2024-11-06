package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.*;
import com.gcd.vacancy.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping
    public ResponseEntity<Void> sendCandidate(@Valid @RequestBody CandidatePostDto candidatePostDto) {
        candidateService.saveCandidate(candidatePostDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CandidateDto>> findAllCandidates() {
        List<CandidateDto> listCandidates = candidateService.findAllCandidates();

        return ResponseEntity.ok(listCandidates);
    }

    @GetMapping("/findCandidateWithApplications/{candidateId}")
    public ResponseEntity<CandidateWithApplicationsDto> findCandiateWithApplication(@PathVariable Long candidateId) {
        CandidateWithApplicationsDto candidate = candidateService.findCandidateWithApplication(candidateId);

        return ResponseEntity.ok(candidate);
    }

    @GetMapping("/findCandidate/{candidateId}")
    public ResponseEntity<CandidateWithCurriculumDto> findCandiate(@PathVariable Long candidateId) {
        CandidateWithCurriculumDto candidate = candidateService.findCandidateWithCurriculum(candidateId);

        return ResponseEntity.ok(candidate);
    }

    @GetMapping("/findCandidate/allInformation/{candidateId}")
    public ResponseEntity<CandidateWithAllInformationDto> findCandidateWithAllInformation(@PathVariable Long candidateId) {
        CandidateWithAllInformationDto candidate = candidateService.findCandidateWithAllInformation(candidateId);

        return ResponseEntity.ok(candidate);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginCandidate(@Valid @RequestBody LoginCandidateDto loginCandidateDto) {
        Map<String, Object> tokenAndLogin = candidateService.loginCandidate(loginCandidateDto);

        return ResponseEntity.ok(tokenAndLogin);
    }

    @GetMapping("/check")
    public ResponseEntity<Map<String, Boolean>> checkUser(@RequestParam String login, @RequestParam String email) {
        Map<String, Boolean> response = candidateService.checkUser(login, email);

        return ResponseEntity.ok(response);
    }

}
