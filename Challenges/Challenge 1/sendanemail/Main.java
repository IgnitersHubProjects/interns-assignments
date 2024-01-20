package sendanemail;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
    	System.out.println("Initiated");
        Email emailSender = new Email();
        String to = "hr@ignitershub.com";
        String from = "iyogeshk31@gmail.com";
        String subject = "Challenge 3 Completed";
        String text = "Name: Yogesh Kumar\nSemester: (8th)Pass Out 2023\nBranch: Computer Science and Engineering\nRoll No.: 22185";
        File file = new File("D:\\image.jpg");      

        //Calling method of Email Class
        boolean b = emailSender.sendEmailWithAttachment(to, from, subject, text, file);
        if (b) {
            System.out.println("Email is sent successfully");
        } else {
            System.out.println("Error in sending email !!");
        }

    }
}