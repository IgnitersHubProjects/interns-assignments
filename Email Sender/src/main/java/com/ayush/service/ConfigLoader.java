package com.ayush.service;



import com.ayush.model.Configuration;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    public static Configuration loadConfig() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream("config.properties");
        properties.load(inputStream);

        Dotenv dotenv = Dotenv.configure().filename("Email Sender/src/main/resources/DEV.env").load();

        Configuration configuration = new Configuration(
                properties.getProperty("mail.smtp.host"),
                properties.getProperty("mail.smtp.port"),
                dotenv.get("MAIL_USERNAME"),
                dotenv.get("MAIL_PASSWORD")

        );


        return configuration;
    }
}
