package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.SkillPostDto;
import com.gcd.vacancy.entity.CandidateEntity;
import com.gcd.vacancy.entity.SkillEntity;
import com.gcd.vacancy.exceptions.customExceptions.NotFoundException;
import com.gcd.vacancy.exceptions.customExceptions.ResourceAlreadyExistsException;
import com.gcd.vacancy.mapper.SkillMapper;
import com.gcd.vacancy.repository.CandidateRepository;
import com.gcd.vacancy.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private SkillMapper skillMapper;

    @Autowired
    private CurriculumServiceImpl curriculumService;

    @Autowired
    private CandidateNotFoundValidation candidateValidationAlreadyExists;

    @Override
    public void saveSkill(Long candidateId, SkillPostDto skillPostDto) {
        CandidateEntity candidateEntity = candidateValidationAlreadyExists.findCandidateById(candidateId);

        Optional<SkillEntity> skillExist = skillRepository.findBySkillIgnoreCase(skillPostDto.getSkill());

        if(skillExist.isPresent()) {
            throw new ResourceAlreadyExistsException("Skill " + "'" + skillPostDto.getSkill() + "'" + " já existe.");
        }

        SkillEntity skillEntity = skillRepository.save(skillMapper.toSkillEntity(skillPostDto));

        curriculumService.associateSkillWithCurriculum(skillEntity, candidateEntity);
    }

    @Override
    public void deleteSkillById(Long skillId) {
        SkillEntity skillEntity = skillRepository.findById(skillId)
                .orElseThrow(() -> new NotFoundException("Skill com id " + skillId + " não encontrada."));

        skillRepository.deleteById(skillId);
    }
}
