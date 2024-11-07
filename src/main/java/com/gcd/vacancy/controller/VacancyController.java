package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.VacancyDto;
import com.gcd.vacancy.dto.VacancyPostDto;
import com.gcd.vacancy.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/vacancy")
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    @PostMapping("/enterpriseId/{enterpriseId}")
    public ResponseEntity<Void> sendVacancy(@PathVariable Long enterpriseId, @RequestBody VacancyPostDto vacancyPostDto) {
        vacancyService.saveVacancy(enterpriseId, vacancyPostDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<VacancyDto>> getAllVacancy() {
        List<VacancyDto> vacancyDtoList = vacancyService.getAllVacancy();

        return ResponseEntity.ok(vacancyDtoList);
    }

    @GetMapping("/enterpriseId/{enterpriseId}")
    public ResponseEntity<List<VacancyDto>> getListVacancyByEntepriseId(@PathVariable Long enterpriseId) {
        List<VacancyDto> listVacancy = vacancyService.getVacancyByEnterpriseId(enterpriseId);

        return ResponseEntity.ok(listVacancy);
    }

    @GetMapping("/{vacancyId}")
    public ResponseEntity<VacancyDto> getVacancyById(@PathVariable Long vacancyId) {
        VacancyDto vacancyDto = vacancyService.getVacancyById(vacancyId);

        return ResponseEntity.ok(vacancyDto);
    }
}
