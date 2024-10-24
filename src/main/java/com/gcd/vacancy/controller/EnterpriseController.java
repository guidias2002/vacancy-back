package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.EnterprisePostDto;
import com.gcd.vacancy.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @PostMapping
    public ResponseEntity sendEnterprise(@RequestBody EnterprisePostDto enterprisePostDto) {
        enterpriseService.saveEnterprise(enterprisePostDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
