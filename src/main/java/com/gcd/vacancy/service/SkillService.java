package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.SkillDto;
import com.gcd.vacancy.dto.SkillPostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkillService {

    void saveSkill(Long candidateId, SkillPostDto skillPostDto);

    void deleteSkillById(Long skillId);

    List<SkillDto> findSkillsByCandidateId(Long candidateId);

    Boolean existsSkill(String skill);
}
