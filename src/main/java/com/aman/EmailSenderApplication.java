
package com.aman;

import com.aman.services.EmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class EmailSenderApplication implements CommandLineRunner {

    private final EmailService emailService;

    public EmailSenderApplication(EmailService emailService) {
        this.emailService = emailService;
    }

    public static void main(String[] args) {
        SpringApplication.run(EmailSenderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        emailService.sendEmail();
    }
}
