package assignment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ApproximateSearch {
    static Path defaultInput() {
        Path file = Path.of("Assignment1", "words.txt");
        return Files.exists(file) ? file : Path.of("words.txt");
    }

    public static void main(String[] args) throws IOException {
        Path file = Path.of(args.length > 0 ? args[0] : defaultInput().toString());
        int k = args.length > 1 ? Integer.parseInt(args[1]) : 3;
        run(readWords(file), k, new Scanner(System.in));
    }

    static List<String> readWords(Path file) throws IOException {
        var unique = new LinkedHashMap<String, String>();
        for (String line : Files.readAllLines(file)) {
            String word = line.strip();
            if (!word.isEmpty()) {
                unique.putIfAbsent(word.toLowerCase(Locale.ROOT), word);
            }
        }
        if (unique.isEmpty()) {
            throw new IllegalArgumentException("The dictionary is empty");
        }

        return List.copyOf(unique.values());
    }

    static void run(List<String> words, int k, Scanner input) {
        if (k <= 0) {
            throw new IllegalArgumentException("k must be positive");
        }
        while (true) {
            System.out.print("Enter a word (blank to quit): ");
            String line = input.hasNextLine() ? input.nextLine() : "";
            if (line.isBlank()) {
                return;
            }
            System.out.println(String.join(", ", closest(words, line.strip(), k)));
        }
    }

    private record Match(int index, int edits) {}

    static List<String> closest(List<String> words, String query, int k) {
        var order = Comparator.comparingInt(Match::edits).thenComparingInt(Match::index);
        var best = new PriorityQueue<>(order.reversed());
        int[] needle = query.toLowerCase(Locale.ROOT).codePoints().toArray();

        for (int i = 0; i < words.size(); i++) {
            int[] word = words.get(i).toLowerCase(Locale.ROOT).codePoints().toArray();
            var match = new Match(i, distance(needle, word));
            if (best.size() < k) {
                best.add(match);
            } else if (order.compare(match, best.element()) < 0) {
                best.remove();
                best.add(match);
            }
        }
        return best.stream().sorted(order).map(match -> words.get(match.index())).toList();
    }

    private static int distance(int[] a, int[] b) {
        if (a.length < b.length) {
            return distance(b, a);
        }

        int[] prev = new int[b.length + 1];
        int[] cur = new int[b.length + 1];
        for (int j = 0; j <= b.length; j++) {
            prev[j] = j;
        }
        for (int i = 1; i <= a.length; i++) {
            cur[0] = i;
            for (int j = 1; j <= b.length; j++) {
                int replace = prev[j - 1] + (a[i - 1] == b[j - 1] ? 0 : 1);
                cur[j] = Math.min(replace, 1 + Math.min(prev[j], cur[j - 1]));
            }
            int[] temp = prev;
            prev = cur;
            cur = temp;
        }
        return prev[b.length];
    }
}
