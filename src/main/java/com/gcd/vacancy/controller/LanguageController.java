package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.LanguageDto;
import com.gcd.vacancy.dto.LanguagePostDto;
import com.gcd.vacancy.service.LanguageService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Void> saveLanguage(@PathVariable Long candidateId, @Valid @RequestBody LanguagePostDto languagePostDto) {
        languageService.saveLanguage(candidateId, languagePostDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{languageId}")
    public ResponseEntity<LanguageDto> updateLanguage(@PathVariable Long languageId, @RequestBody LanguagePostDto languagePostDto) {
        LanguageDto languageDto = languageService.updateLanguageDto(languageId, languagePostDto);

        return ResponseEntity.ok(languageDto);
    }

    @DeleteMapping("/delete/{languageId}")
    public ResponseEntity<Void> deleteLanguageById(@PathVariable Long languageId) {
        languageService.deleteLanguage(languageId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
