package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.AboutMeDto;
import com.gcd.vacancy.dto.AboutMePostDto;
import org.springframework.stereotype.Service;

@Service
public interface AboutMeService {

    void saveAboutMe(Long candidateId, AboutMePostDto aboutMePostDto);

    AboutMeDto updateAboutMe(Long candidateId, AboutMePostDto aboutMePostDto);

    AboutMeDto findAboutMeByCandidateId(Long candidateId);
}
