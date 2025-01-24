package com.springemail.SpringEmail;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;


@RestController
public class EmailController {

    private final JavaMailSender mailSender;
    String name = "Srishti Tiwari";
    String sem = "8th";
    String branch = "Computer Science and Engineering";
    String rollNo = "210050101057";

    public EmailController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @RequestMapping("/send-email-with-attachment")
    public String sendEmailWithAttachment() {
        
        try {
            String body = String.format("Name: %s%nSemester: %s%nBranch: %s%nRoll Number: %s", name, sem, branch, rollNo);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("maisrishtihoon@gmail.com");
            helper.setTo("hr@ignitershub.com");
            helper.setSubject("Challenge 3 Completed");
            helper.setText(body);

            helper.addAttachment("srishti.png", new File("C:\\Users\\LENOVO\\Desktop\\srishti.png"));

            mailSender.send(message);
            return "success!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
