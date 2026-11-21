package assignment;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import javax.imageio.ImageIO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

class SendEmail {
    static void run(String[] args, Scanner input) throws Exception {
        Path image = Path.of(args.length > 0 ? args[0] : ask(input, "Image path"));
        String imageType = imageType(image);
        String from = args.length > 1 ? args[1] : setting("SPRING_MAIL_USERNAME", input, "Sender email");
        String to = args.length > 2 ? args[2] : setting("ASSIGNMENT_EMAIL_TO", input, "Recipient email");
        String body = """
                Name: %s
                Semester: %s
                Branch: %s
                Roll number: %s
                """.formatted(setting("STUDENT_NAME", input, "Name"),
                setting("STUDENT_SEMESTER", input, "Semester"),
                setting("STUDENT_BRANCH", input, "Branch"),
                setting("STUDENT_ROLL_NUMBER", input, "Roll number"));

        var sender = new JavaMailSenderImpl();
        sender.setHost(env("SPRING_MAIL_HOST", "smtp.gmail.com"));
        sender.setPort(Integer.parseInt(env("SPRING_MAIL_PORT", "587")));
        sender.setUsername(from);

        String auth = env("SPRING_MAIL_SMTP_AUTH", "true");
        String tls = env("SPRING_MAIL_SMTP_STARTTLS", "true");
        var properties = new Properties();
        properties.setProperty("mail.smtp.auth", auth);
        properties.setProperty("mail.smtp.starttls.enable", tls);
        properties.setProperty("mail.smtp.starttls.required", tls);
        properties.setProperty("mail.smtp.ssl.checkserveridentity", "true");
        properties.setProperty("mail.smtp.connectiontimeout", "10000");
        properties.setProperty("mail.smtp.timeout", "10000");
        properties.setProperty("mail.smtp.writetimeout", "10000");
        sender.setJavaMailProperties(properties);
        if (Boolean.parseBoolean(auth)) {
            sender.setPassword(appPassword());
        }

        var message = sender.createMimeMessage();
        var mail = new MimeMessageHelper(message, true, "UTF-8");
        mail.setValidateAddresses(true);
        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject("Challenge 3 Completed");
        mail.setText(body);
        mail.addAttachment(image.getFileName().toString(), new FileSystemResource(image), imageType);
        sender.send(message);
        System.out.println("Email sent to " + to);
    }

    private static String imageType(Path image) throws Exception {
        String name = image.getFileName().toString().toLowerCase(Locale.ROOT);
        String format;
        if (name.endsWith(".png")) {
            format = "png";
        } else if (name.endsWith(".jpg") || name.endsWith(".jpeg")) {
            format = "jpeg";
        } else {
            throw new IllegalArgumentException("Attach a PNG, JPG or JPEG image.");
        }

        try (var stream = ImageIO.createImageInputStream(image.toFile())) {
            if (stream == null) {
                throw new IllegalArgumentException("Could not open " + image);
            }
            var readers = ImageIO.getImageReaders(stream);
            if (!readers.hasNext()) {
                throw new IllegalArgumentException("The attachment is not a readable image.");
            }
            var reader = readers.next();
            try {
                if (!format.equalsIgnoreCase(reader.getFormatName())) {
                    throw new IllegalArgumentException("The image format does not match its extension.");
                }
                reader.setInput(stream);
                reader.read(0);
            } finally {
                reader.dispose();
            }
        }
        return "image/" + format;
    }

    private static String appPassword() {
        String value = env("SPRING_MAIL_PASSWORD", "");
        if (!value.isEmpty()) {
            return value.replace(" ", "");
        }
        var console = System.console();
        if (console == null) {
            throw new IllegalArgumentException("Run from a terminal to enter your Gmail app password.");
        }
        char[] password = console.readPassword("Gmail app password (hidden): ");
        if (password == null || password.length == 0) {
            throw new IllegalArgumentException("Enter your Gmail app password.");
        }
        try {
            String result = new String(password).replace(" ", "");
            if (result.isEmpty()) {
                throw new IllegalArgumentException("Enter your Gmail app password.");
            }
            return result;
        } finally {
            Arrays.fill(password, '\0');
        }
    }

    private static String setting(String name, Scanner input, String label) {
        String value = env(name, "");
        return value.isEmpty() ? ask(input, label) : value;
    }

    private static String ask(Scanner input, String label) {
        System.out.print(label + ": ");
        if (input.hasNextLine()) {
            String value = input.nextLine().strip();
            if (!value.isEmpty()) {
                return value;
            }
        }
        throw new IllegalArgumentException(label + " is required.");
    }

    private static String env(String name, String fallback) {
        String value = System.getenv(name);
        return value == null || value.isBlank() ? fallback : value.strip();
    }
}
