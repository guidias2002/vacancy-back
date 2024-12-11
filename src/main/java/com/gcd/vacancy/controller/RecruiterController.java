package com.gcd.vacancy.controller;

import com.gcd.vacancy.entity.RecruiterEntity;
import com.gcd.vacancy.service.RecruiterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @PostMapping("/createAccount/{enterpriseId}")
    public ResponseEntity<RecruiterEntity> saveRecruiter(@Valid @RequestBody String email, @PathVariable Long enterpriseId) {
        RecruiterEntity recruiterEntity = recruiterService.saveRecruiter(email, enterpriseId);

        return ResponseEntity.ok(recruiterEntity);
    }
}
