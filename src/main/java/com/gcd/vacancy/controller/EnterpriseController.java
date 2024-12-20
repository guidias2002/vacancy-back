package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.*;
import com.gcd.vacancy.service.EnterpriseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @PostMapping
    public ResponseEntity<Void> sendEnterprise(@Valid @RequestBody EnterprisePostDto enterprisePostDto) {
        enterpriseService.saveEnterprise(enterprisePostDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{enterpriseId}")
    public ResponseEntity<EnterpriseDto> getEnterpriseById(@PathVariable Long enterpriseId) {
        EnterpriseDto enterprise = enterpriseService.getEnterpriseById(enterpriseId);

        return ResponseEntity.ok(enterprise);
    }

    @GetMapping("/{enterpriseId}/vacanciesByEnterprise")
    public ResponseEntity<EnterpriseWithListVacanciesDto> getVacanciesByEnterprise(@PathVariable Long enterpriseId) {
        EnterpriseWithListVacanciesDto vacanciesByEnterprise = enterpriseService.getVacanciesByEnterprise(enterpriseId);

        return ResponseEntity.ok(vacanciesByEnterprise);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EnterpriseDto>> getAllEnterprise() {
        List<EnterpriseDto> enterpriseDtoList = enterpriseService.getAllEnterprise();

        return ResponseEntity.ok(enterpriseDtoList);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginEnterprise(@Valid @RequestBody LoginEnterpriseDto loginEnterpriseDto) {
        Map<String, Object> tokenAndLogin = enterpriseService.loginEnterprise(loginEnterpriseDto);

        return ResponseEntity.ok(tokenAndLogin);
    }

    @PutMapping("/disableRecruiterAccount/enterpriseId/{enterpriseId}/recruiterId/{recruiterId}")
    public ResponseEntity<RecruiterDto> disableRecruiterAccount(@PathVariable Long enterpriseId, @PathVariable Long recruiterId) {

        return ResponseEntity.ok(enterpriseService.disableRecruiterAccount(enterpriseId, recruiterId));
    }

    @PutMapping("/enableRecruiterAccount/enterpriseId/{enterpriseId}/recruiterId/{recruiterId}")
    public ResponseEntity<Void> enableRecruiterAccount(@PathVariable Long enterpriseId, @PathVariable Long recruiterId) {
        enterpriseService.enableRecruiterAccount(enterpriseId, recruiterId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
