package com.gcd.vacancy.service;

import com.gcd.vacancy.entity.RecruiterEntity;
import org.springframework.stereotype.Service;

@Service
public interface RecruiterService {

    RecruiterEntity saveRecruiter(String email, Long enterpriseId);

    void sendInvitation(String email, String password);
}
