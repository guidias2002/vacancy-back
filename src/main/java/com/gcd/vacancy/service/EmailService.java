package com.gcd.vacancy.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    String sendInvitationToRecruiter(String recipientEmail, String subject, String message, String enterpriseName, String name);
}
