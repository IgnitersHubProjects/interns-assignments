package assignment;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ArithmeticExpressions {
    static Path defaultInput() {
        Path file = Path.of("Assignment2", "expressions.txt");
        return Files.exists(file) ? file : Path.of("expressions.txt");
    }

    public static void main(String[] args) throws IOException {
        Path input = Path.of(args.length > 0 ? args[0] : defaultInput().toString());
        Path output = args.length > 1 ? Path.of(args[1]) : input.resolveSibling("answers.txt");
        evaluate(input, output);
        System.out.println("Answers written to " + output);
    }

    static void evaluate(Path input, Path output) throws IOException {
        Path target = output.toAbsolutePath().normalize();
        if (Files.exists(target) && Files.isSameFile(input, target)) {
            throw new IllegalArgumentException("Input and output must be different files");
        }

        Path temp = Files.createTempFile(target.getParent(), ".answers-", ".tmp");
        try {
            try (var reader = Files.newBufferedReader(input); var writer = Files.newBufferedWriter(temp)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.isBlank()) {
                        writer.write(solveLine(line));
                    }
                    writer.newLine();
                }
            }
            Files.move(temp, target, StandardCopyOption.REPLACE_EXISTING);
        } finally {
            Files.deleteIfExists(temp);
        }
    }

    private static String solveLine(String line) {
        String expression = line.stripTrailing();
        if (expression.endsWith("=")) {
            expression = expression.substring(0, expression.length() - 1).stripTrailing();
        }
        try {
            return expression + " = " + new Parser(expression).parse().stripTrailingZeros().toPlainString();
        } catch (IllegalArgumentException | ArithmeticException e) {
            return expression + " = ERROR (" + e.getMessage() + ")";
        }
    }

    private static final class Parser {
        private static final MathContext MC = MathContext.DECIMAL128;
        private final String text;
        private int pos;

        Parser(String text) {
            this.text = text.replace('–', '-').replace('−', '-');
        }

        BigDecimal parse() {
            BigDecimal result = expression(1, 0);
            peek();
            if (pos < text.length()) {
                throw error("Unexpected character");
            }
            return result;
        }

        private BigDecimal expression(int minimum, int depth) {
            if (depth > 100) {
                throw error("Expression nested too deeply");
            }
            char sign = peek();
            BigDecimal result;
            if (sign == '+' || sign == '-') {
                pos++;
                result = expression(3, depth + 1);
                if (sign == '-') {
                    result = result.negate();
                }
            } else {
                result = atom(depth);
            }

            while (true) {
                char op = peek();
                boolean implicit = opening(op);
                int precedence = switch (op) {
                    case '+', '-' -> 1;
                    case '*', '/', '(', '[', '{' -> 2;
                    case '^' -> 3;
                    default -> 0;
                };
                if (precedence < minimum) {
                    return result;
                }
                if (!implicit) {
                    pos++;
                }
                BigDecimal right = expression(precedence + (op == '^' ? 0 : 1), depth + 1);
                result = switch (op) {
                    case '+' -> result.add(right, MC);
                    case '-' -> result.subtract(right, MC);
                    case '/' -> result.divide(right, MC);
                    case '^' -> power(result, right);
                    default -> result.multiply(right, MC);
                };
            }
        }

        private BigDecimal power(BigDecimal base, BigDecimal exponent) {
            if (exponent.abs().compareTo(BigDecimal.valueOf(1000)) > 0) {
                throw error("Exponent must be from -1000 to 1000");
            }
            if (exponent.stripTrailingZeros().scale() <= 0) {
                return base.pow(exponent.intValueExact(), MC);
            }
            double result = Math.pow(base.doubleValue(), exponent.doubleValue());
            if (!Double.isFinite(result)) {
                throw error("Fractional power has no finite real result");
            }
            return BigDecimal.valueOf(result);
        }

        private BigDecimal atom(int depth) {
            char next = peek();
            if (opening(next)) {
                pos++;
                char closing = switch (next) {
                    case '(' -> ')';
                    case '[' -> ']';
                    default -> '}';
                };
                BigDecimal result = expression(1, depth + 1);
                if (peek() != closing) {
                    throw error("Expected '" + closing + "'");
                }
                pos++;
                return result;
            }

            int start = pos;
            while (pos < text.length() && digit(text.charAt(pos))) {
                pos++;
            }
            if (pos < text.length() && text.charAt(pos) == '.') {
                pos++;
                while (pos < text.length() && digit(text.charAt(pos))) {
                    pos++;
                }
            }
            if (pos == start || (pos == start + 1 && next == '.')) {
                throw error("Expected a number or an opening bracket");
            }
            return new BigDecimal(text.substring(start, pos), MC);
        }

        private char peek() {
            while (pos < text.length() && Character.isWhitespace(text.charAt(pos))) {
                pos++;
            }
            return pos < text.length() ? text.charAt(pos) : '\0';
        }

        private static boolean opening(char ch) {
            return ch == '(' || ch == '[' || ch == '{';
        }

        private static boolean digit(char ch) {
            return ch >= '0' && ch <= '9';
        }

        private IllegalArgumentException error(String message) {
            return new IllegalArgumentException(message + " at column " + (pos + 1));
        }
    }
}
