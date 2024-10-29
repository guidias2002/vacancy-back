package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.VacancyPostDto;
import com.gcd.vacancy.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/vacancy")
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    @PostMapping("/enterpriseId/{enterpriseId}")
    public ResponseEntity sendVacancy(@PathVariable Long enterpriseId, @RequestBody VacancyPostDto vacancyPostDto) {
        vacancyService.saveVacancy(enterpriseId, vacancyPostDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
