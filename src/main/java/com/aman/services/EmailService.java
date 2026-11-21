
package com.aman.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.mail.from}")
    private String senderEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail() throws Exception {

        MimeMessage message = mailSender.createMimeMessage();

        // true means the email supports attachments
        MimeMessageHelper helper =
                new MimeMessageHelper(message, true);

        helper.setFrom(senderEmail);
        helper.setTo("hr@ignitershub.com");
        helper.setSubject("Challenge 3 Completed");

        String body = """
                Name: Aman Rautela
                Semester: Third
                Branch: CS
                Roll Number: 2
                """;

        helper.setText(body);

        // Attach the image from resources/attachments
        ClassPathResource image =
                new ClassPathResource("attachments/test.jpg");

        helper.addAttachment("test.jpg", image);

        mailSender.send(message);

        System.out.println("Email sent successfully!");
    }
}
