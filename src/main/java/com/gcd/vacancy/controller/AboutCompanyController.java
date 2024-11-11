package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.AboutCompanyPostDto;
import com.gcd.vacancy.service.AboutCompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/about-enterprise")
public class AboutCompanyController {

    @Autowired
    private AboutCompanyService aboutCompanyService;

    @PostMapping("/{enterpriseId}")
    public ResponseEntity<Void> saveAboutCompany(@PathVariable Long enterpriseId, @Valid @RequestBody AboutCompanyPostDto aboutCompanyPostDto) {
        aboutCompanyService.saveAboutCompany(enterpriseId, aboutCompanyPostDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
