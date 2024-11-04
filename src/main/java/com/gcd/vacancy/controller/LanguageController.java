package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.LanguagePostDto;
import com.gcd.vacancy.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/language")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @PostMapping("/candidateId/{candidateId}")
    public ResponseEntity<Void> saveLanguage(@PathVariable Long candidateId, @RequestBody LanguagePostDto languagePostDto) {
        languageService.saveLanguage(candidateId, languagePostDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
