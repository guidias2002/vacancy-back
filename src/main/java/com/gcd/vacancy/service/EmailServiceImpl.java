package com.gcd.vacancy.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Async
    @Override
    public String sendInvitationToRecruiter(String recipientEmail, String password, String subject, String enterpriseName) {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom(sender);
            helper.setTo(recipientEmail);
            helper.setSubject(subject);

            String htmlContent = "<!DOCTYPE html>" +
                    "<html>" +
                    "<body>" +
                    "<h1 style='color: #007bff;'>Convite de Acesso à Plataforma</h1>" +
                    "<p>Olá,</p>" +
                    "<p>Você recebeu um convite para acessar a plataforma.</p>" +
                    "<p><b>Email:</b> " + recipientEmail + "</p>" +
                    "<p><b>Senha:</b> " + password + "</p>" +
                    "<p>Acesse pelo site: <a href='https://localhost:8080'>https://localhost:8080</a></p>" +
                    "<br>" +
                    "<p>Atenciosamente,</p>" +
                    "<p><b>Equipe "+ enterpriseName + "</b></p>" +
                    "</body>" +
                    "</html>";

            helper.setText(htmlContent, true);

            javaMailSender.send(mimeMessage);

            return "E-mail enviado com sucesso.";
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar o e-mail: " + e.getMessage(), e);
        }
    }
}
