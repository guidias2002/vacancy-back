package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.AboutMePostDto;
import com.gcd.vacancy.service.AboutMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aboutMe")
public class AboutMeController {

    @Autowired
    private AboutMeService aboutMeService;

    @PostMapping("/{candidateId}")
    public ResponseEntity<Void> sendAboutMe(@PathVariable Long candidateId, @RequestBody AboutMePostDto aboutMePostDto) {
        aboutMeService.saveAboutMe(candidateId, aboutMePostDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
