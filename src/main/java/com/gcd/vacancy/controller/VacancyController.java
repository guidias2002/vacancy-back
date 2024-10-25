package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.VacancyPostDto;
import com.gcd.vacancy.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vacancy")
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    @PostMapping
    public ResponseEntity sendVacancy(@RequestBody VacancyPostDto vacancyPostDto) {
        vacancyService.saveVacancy(vacancyPostDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
