package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.SkillPostDto;
import org.springframework.stereotype.Service;

@Service
public interface SkillService {

    void saveSkill(Long candidateId, SkillPostDto skillPostDto);

    void deleteSkillById(Long skillId);
}
