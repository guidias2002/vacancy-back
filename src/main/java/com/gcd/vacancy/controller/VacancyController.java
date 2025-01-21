package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.VacancyBasicInformationDto;
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

    @GetMapping("/getAllByActive")
    public ResponseEntity<List<VacancyDto>> getAllVacancy() {

        return ResponseEntity.ok(vacancyService.getAllVacancyByStatusActive());
    }

    @GetMapping("/findVacancyByEnterpriseId/allInformation/{enterpriseId}")
    public ResponseEntity<List<VacancyDto>> getListVacancyByEnterpriseIdAllInformation(@PathVariable Long enterpriseId) {

        return ResponseEntity.ok(vacancyService.getVacancyByEnterpriseIdAllInformation(enterpriseId));
    }

    @GetMapping("/findVacancyByEnterpriseId/basicInformation/{enterpriseId}")
    public ResponseEntity<List<VacancyBasicInformationDto>> getListVacancyByEnterpriseIdBasicInformation(@PathVariable Long enterpriseId) {

        return ResponseEntity.ok(vacancyService.getVacancyByEnterpriseIdBasicInformation(enterpriseId));
    }

    @GetMapping("/{vacancyId}")
    public ResponseEntity<VacancyDto> getVacancyById(@PathVariable Long vacancyId) {

        return ResponseEntity.ok(vacancyService.getVacancyById(vacancyId));
    }
}
