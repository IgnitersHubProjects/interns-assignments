package org.example;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.lang.*;
import java.util.*;
import java.io.*;


public class Main {

    public static boolean sendMail(String to, String from, String subject, String message, File file) {
        boolean flag = false;

        if (!file.getName().toLowerCase().endsWith(".png") && !file.getName().toLowerCase().endsWith(".jpg") &&
                (!file.getName().toLowerCase().endsWith(".jpeg"))) {
            System.out.println("Unsupported file type");
            return false;
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        String username = "******************";
        String password = "******************";

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message msz = new MimeMessage(session);
            msz.setFrom(new InternetAddress(from));
            msz.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msz.setSubject(subject);

            MimeMultipart multipart = new MimeMultipart();

            MimeBodyPart textpart = new MimeBodyPart();
            textpart.setText(message);
            multipart.addBodyPart(textpart);

            if (file != null) {

                MimeBodyPart filepart = new MimeBodyPart();
                filepart.attachFile(file);
                multipart.addBodyPart(filepart);

            }
            msz.setContent(multipart);

            Transport.send(msz);
            flag = true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return flag;
    }

    public static void main(String[] args) {

        String to = "hr@**************";
        String from = "ayush.**********@gmail.com";
        String subject = "Challenge 3 Completed";
        String message =  "Name: Ayush Khanduri"  + "\n" +
                "Semester: 7"  + "\n" +
                "Branch: CSE"  + "\n" +
                "Roll Number: 22";

        File file = null;
        file = new File("C:/Users/HP/OneDrive/Pictures/Screenshots/Screenshot 2024-07-04 191009.png");

        if (sendMail(to, from, subject, message, file)) {
            System.out.println("Mail sent succesfully");
        } else {
            System.out.println("Mail not sent");
        }
    }
}