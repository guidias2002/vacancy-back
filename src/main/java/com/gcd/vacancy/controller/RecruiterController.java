package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.*;
import com.gcd.vacancy.entity.RecruiterEntity;
import com.gcd.vacancy.service.RecruiterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @PostMapping("/createAccount/{enterpriseId}")
    public ResponseEntity<RecruiterDto> saveRecruiterAndSendEmail(@Valid @RequestBody RecruiterPostDto recruiterPostDto, @PathVariable Long enterpriseId) {

        return ResponseEntity.ok(recruiterService.saveRecruiterAndSendEmail(recruiterPostDto, enterpriseId));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginRecruiter(@Valid @RequestBody RecruiterLoginDto recruiterLoginDto) {

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

    @GetMapping("findRecruiterById/{recruiterId}")
    public ResponseEntity<RecruiterDto> findRecruiterById(@PathVariable Long recruiterId) {

        return ResponseEntity.ok(recruiterService.findRecruiterById(recruiterId));
    }

    @PostMapping("/resendEmailtoRecruiter/{recruiterId}")
    public ResponseEntity<Void> resendEmailToRecruiter(@PathVariable Long recruiterId) {
        recruiterService.resendEmailToRecruiter(recruiterId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
