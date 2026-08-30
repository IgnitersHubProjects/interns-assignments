package com.ayush.service;

import com.ayush.model.Attachment;
import com.ayush.config.Configuration;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class EmailSender {
    private Session session;
    public EmailSender(Configuration config) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host",config.getHost() );
        properties.put("mail.smtp.port", Integer.parseInt(config.getPort()));
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);

        session = Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(config.getUsername(),config.getPassword());
                    }
                }
        );
    }

    public void send(String senderMailAddress, String receiverMailAddress, String subject, String body, List<Attachment> attachments) throws MessagingException, IOException {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderMailAddress));

        message.setSubject(subject);

        Multipart multipart = new MimeMultipart();

        MimeBodyPart emailBody = new MimeBodyPart();
        emailBody.setText(body);
        multipart.addBodyPart(emailBody);


        for (Attachment attachment : attachments){
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            File file = new File(attachment.getFileName());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(attachment.getData());
            mimeBodyPart.attachFile(file);
            multipart.addBodyPart(mimeBodyPart);
        }

        message.setContent(multipart);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiverMailAddress));
        Transport.send(message);
    }
}
