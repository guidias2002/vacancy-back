package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.LanguageDto;
import com.gcd.vacancy.dto.LanguagePostDto;
import com.gcd.vacancy.dto.LanguageUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LanguageService {

    void saveLanguage(Long candidateId, LanguagePostDto languagePostDto);

    void deleteLanguage(Long languageId);

    List<LanguageDto> findLanguagesByCandidateId(Long candidateId);

    LanguageDto updateLanguage(Long languageId, LanguageUpdateDto languageUpdateDto);

    Boolean existsByLanguage(String language);
}
