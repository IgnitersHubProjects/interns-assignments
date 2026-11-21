package assignment;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        if (args.length == 0) {
            System.out.print("""
                    1. Approximate search
                    2. Arithmetic expressions
                    3. Send email
                    4. Palindrome
                    Choose a challenge:""");
            if (!input.hasNextLine()) {
                return;
            }
            args = new String[] {input.nextLine().strip()};
        }

        try {
            switch (args[0]) {
                case "1", "search" -> {
                    var words = ApproximateSearch.readWords(Path.of(arg(args, 1, ApproximateSearch.defaultInput().toString())));
                    int k = Integer.parseInt(arg(args, 2, "3"));
                    ApproximateSearch.run(loadDictionary(words), k, input);
                }
                case "2", "evaluate" -> ArithmeticExpressions.main(java.util.Arrays.copyOfRange(args, 1, args.length));
                case "3", "email" -> SendEmail.run(java.util.Arrays.copyOfRange(args, 1, args.length), input);
                case "4", "palindrome" -> PalindromeChecker.run(input);
                default -> throw new IllegalArgumentException("Choose 1, 2, 3 or 4.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static String arg(String[] args, int index, String fallback) {
        return index < args.length ? args[index] : fallback;
    }

    private static ConfigurableApplicationContext startSpring() {
        var app = new SpringApplication(Main.class);
        app.setDefaultProperties(Map.of(
                "spring.main.banner-mode", "off",
                "logging.level.root", "WARN",
                "spring.datasource.url", "jdbc:h2:mem:assignment",
                "spring.jpa.hibernate.ddl-auto", "create-drop"));
        return app.run();
    }

    private static List<String> loadDictionary(List<String> words) {
        try (var app = startSpring()) {
            var db = app.getBean(EntityManagerFactory.class).createEntityManager();
            try {
                db.getTransaction().begin();
                for (int i = 0; i < words.size(); i++) {
                    db.persist(new Word(i, words.get(i)));
                }
                db.getTransaction().commit();
                return db.createQuery("select w.value from Word w order by w.id", String.class)
                        .getResultList();
            } finally {
                db.close();
            }
        }
    }

    @Entity(name = "Word")
    public static class Word {
        @Id
        private int id;

        @Lob
        @jakarta.persistence.Column(name = "word_text")
        private String value;

        protected Word() {}

        Word(int id, String value) {
            this.id = id;
            this.value = value;
        }
    }
}
