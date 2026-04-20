package com.quizify.quizify.services;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendWelcomeEmail(String toEmail, Object username) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            // Contenu HTML de l'email
            String htmlContent = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; border: 1px solid #e0e0e0; border-radius: 10px; padding: 20px;'>"
                    +
                    "<div style='text-align: center; background-color: #2c3e50; padding: 10px; border-radius: 8px 8px 0 0;'>"
                    +
                    "<h1 style='color: #ffffff; margin: 0;'>Quizify</h1>" +
                    "</div>" +
                    "<div style='padding: 20px; text-align: center;'>" +
                    "<h2>Bienvenue, " + username + " ! 👋</h2>" +
                    "<p style='color: #555; font-size: 16px;'>Ton compte a été créé avec succès. Prépare-toi à relever de nouveaux défis techniques !</p>"
                    +
                    "<p style='font-size: 12px; color: #999;'>Ceci est un email automatique, merci de ne pas y répondre.</p>"
                    +
                    "</div>" +
                    "<div style='text-align: center; border-top: 1px solid #eee; padding-top: 20px; font-size: 12px; color: #7f8c8d;'>"
                    +
                    "Développé par L'équipe Quizify" +
                    "</div>" +
                    "</div>";

            helper.setText(htmlContent, true); // Le "true" ici est CRUCIAL pour activer le HTML
            helper.setTo(toEmail);
            helper.setSubject("Bienvenue sur Quizify ! 🚀");
            helper.setFrom("noreply@quizify.com");

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            System.err.println("Erreur email HTML : " + e.getMessage());
        }
    }

    // --- MÉTHODE À AJOUTER POUR CORRIGER L'ERREUR DE COMPILATION ---
    public void sendVerificationCode(String toEmail, String code) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            String htmlContent = 
                "<div style='font-family: Arial, sans-serif; padding: 20px; border: 1px solid #eee; border-radius: 10px; max-width: 500px; text-align: center;'>" +
                    "<h2 style='color: #2c3e50;'>Code de Vérification 🔐</h2>" +
                    "<p>Bonjour,</p>" +
                    "<p>Voici votre code de sécurité pour accéder à votre compte <strong>Quizify</strong> :</p>" +
                    "<div style='background-color: #f8f9fa; padding: 20px; border-radius: 10px; margin: 20px 0; border: 2px dashed #23a6d5;'>" +
                        "<span style='font-size: 32px; font-weight: bold; color: #23a6d5; letter-spacing: 5px;'>" + code + "</span>" +
                    "</div>" +
                    "<p style='font-size: 13px; color: #7f8c8d;'>Ce code est confidentiel. Ne le partagez jamais.</p>" +
                "</div>";

            helper.setText(htmlContent, true);
            helper.setTo(toEmail);
            helper.setSubject("Votre code de sécurité Quizify");
            helper.setFrom("noreply@quizify.com");

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            System.err.println("Erreur envoi email code de vérification : " + e.getMessage());
        }
    }

    public void sendForgotUsernameEmail(String toEmail, String username) {
    try {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String htmlContent = 
            "<div style='font-family: Arial, sans-serif; padding: 20px; border: 1px solid #eee; border-radius: 10px; max-width: 500px;'>" +
                "<h2 style='color: #2c3e50;'>Récupération d'identifiant 🔍</h2>" +
                "<p>Bonjour,</p>" +
                "<p>Vous avez demandé à recevoir votre nom d'utilisateur pour accéder à <strong>Quizify</strong>.</p>" +
                "<div style='background-color: #f8f9fa; padding: 15px; text-align: center; border-radius: 5px; margin: 20px 0;'>" +
                    "Votre nom d'utilisateur est : <br>" +
                    "<strong style='font-size: 22px; color: #e67e22;'>" + username + "</strong>" +
                "</div>" +
                "<p style='font-size: 13px; color: #7f8c8d;'>Si vous n'êtes pas à l'origine de cette demande, vous pouvez ignorer cet email.</p>" +
            "</div>";

        helper.setText(htmlContent, true);
        helper.setTo(toEmail);
        helper.setSubject("Votre identifiant Quizify");
        helper.setFrom("noreply@quizify.com");

        mailSender.send(mimeMessage);
    } catch (Exception e) {
        System.err.println("Erreur envoi email identifiant : " + e.getMessage());
    }
    
}
}