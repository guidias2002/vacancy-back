package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.VacancyDto;
import com.gcd.vacancy.dto.VacancyPostDto;
import com.gcd.vacancy.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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
