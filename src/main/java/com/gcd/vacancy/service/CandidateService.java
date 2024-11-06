package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CandidateService {
    void saveCandidate(CandidatePostDto candidatePostDto);

    List<CandidateDto> findAllCandidates();

    CandidateWithApplicationsDto findCandidateWithApplication(Long candidateId);

    CandidateWithCurriculumDto findCandidateWithCurriculum(Long candidateId);

    CandidateWithAllInformationDto findCandidateWithAllInformation(Long candidateId);

    Map<String, Object> loginCandidate(LoginCandidateDto loginCandidateDto);

    Map<String, Boolean> checkUser(String login, String email);

}
