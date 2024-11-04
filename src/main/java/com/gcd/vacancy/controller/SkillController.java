package com.gcd.vacancy.controller;

import com.gcd.vacancy.dto.SkillPostDto;
import com.gcd.vacancy.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @PostMapping("/candidateId/{candidateId}")
    public ResponseEntity<Void> saveSkill(@PathVariable Long candidateId, @Valid @RequestBody SkillPostDto skillPostDto) {
        skillService.saveSkill(candidateId, skillPostDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{skillId}")
    public ResponseEntity<Void> deleteSkillById(@PathVariable Long skillId) {
        skillService.deleteSkillById(skillId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
