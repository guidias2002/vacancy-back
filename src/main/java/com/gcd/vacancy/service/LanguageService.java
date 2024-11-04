package com.gcd.vacancy.service;

import com.gcd.vacancy.dto.LanguagePostDto;
import org.springframework.stereotype.Service;

@Service
public interface LanguageService {

    void saveLanguage(Long candidateId, LanguagePostDto languagePostDto);
}
