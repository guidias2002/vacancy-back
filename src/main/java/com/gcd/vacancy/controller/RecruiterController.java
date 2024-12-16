package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.*;
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
    public ResponseEntity<RecruiterEntity> saveRecruiter(@Valid @RequestBody RecruiterPostDto recruiterPostDto, @PathVariable Long enterpriseId) {

        return ResponseEntity.ok(recruiterService.saveRecruiter(recruiterPostDto, enterpriseId));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginRecruiter(@Valid @RequestBody RecruiterLoginDto recruiterLoginDto) {

        return ResponseEntity.ok(recruiterService.loginRecruiter(recruiterLoginDto));
    }

    @GetMapping("/findAllRecruiters")
    public ResponseEntity<List<RecruiterDto>> findAllRecruiters() {

        return ResponseEntity.ok(recruiterService.findAllRecruiters());
    }

    @GetMapping("/findAllRecruitersByEnterpriseId/{enterpriseId}")
    public ResponseEntity<List<RecruiterDto>> findAllRecruitersByEnterpriseId(@PathVariable Long enterpriseId) {

        return ResponseEntity.ok(recruiterService.findAllRecruitersByEnterpriseId(enterpriseId));
    }

    @PutMapping("/updatePassword/{recruiterId}")
    public ResponseEntity<RecruiterEmailAndPasswordDto> updateRecruiterPassword(@PathVariable Long recruiterId, @Valid @RequestBody RecruiterUpdatedPasswordDto updatedPasswordDto) {

        return ResponseEntity.ok(recruiterService.updateRecruiterPassword(recruiterId, updatedPasswordDto));
    }

}
