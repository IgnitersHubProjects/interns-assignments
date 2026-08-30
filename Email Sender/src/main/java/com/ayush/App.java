package com.ayush;

import com.ayush.config.ConfigLoader;
import com.ayush.model.Attachment;
import com.ayush.config.Configuration;
import com.ayush.service.EmailSender;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )  {
        try {
            List<Attachment> attachments = new ArrayList<>();
            Attachment image = new Attachment("myPic.jpeg","Email Sender/src/data/220108019.jpeg");
            attachments.add(image);

            Configuration configuration = ConfigLoader.loadConfig();
            EmailSender emailSender = new EmailSender(configuration);
            emailSender.send(
                    "rayush2504@gmail.com",
                    "rmaya790@gmail.com",
                    "“Challenge 3 Completed",
                    String.format(
                            "Name : AYUSH RATHORE\n"+
                            "semester : ALL COMPLETED\n"+
                            "branch : INFORMATION TECHNOLOGY\n"+
                            "Roll Number : 220108019"
                    ),
                    attachments
                    );
            System.out.printf("\nmessage sent\n");
        }catch (Exception exception){
            System.out.printf("\n%s\n", exception.getMessage());
        }



    }
}
