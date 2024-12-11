package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.RecruiterDto;
import com.gcd.vacancy.dto.RecruiterLoginDto;
import com.gcd.vacancy.entity.RecruiterEntity;
import com.gcd.vacancy.service.RecruiterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @PostMapping("/createAccount/{enterpriseId}")
    public ResponseEntity<RecruiterEntity> saveRecruiter(@Valid @RequestBody String email, @PathVariable Long enterpriseId) {

        return ResponseEntity.ok(recruiterService.saveRecruiter(email, enterpriseId));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginRecruiter(@Valid @RequestBody RecruiterLoginDto recruiterLoginDto) {

        return ResponseEntity.ok(recruiterService.loginRecruiter(recruiterLoginDto));
    }

    @GetMapping("/findAllRecruiters")
    public ResponseEntity<List<RecruiterDto>> findAllRecruiters() {

        return ResponseEntity.ok(recruiterService.findAllRecruiters());
    }
}
