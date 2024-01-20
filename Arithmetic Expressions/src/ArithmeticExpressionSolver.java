import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class ArithmeticExpressionSolver {

    public static void main(String[] args) throws IOException {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String result = evaluateExpression(line);
                writer.write(line + " " + result + "\n");
            }
        }
    }

    private static String evaluateExpression(String expression) {
        Stack<Double> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch)) {
                int start = i;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    i++;
                }
                operands.push(Double.parseDouble(expression.substring(start, i)));
                i--; // Backtrack for next iteration
            } else if (isOperator(ch)) {
                while (!operators.isEmpty() && hasPrecedence(ch, operators.peek())) {
                    operands.push(performOperation(operators.pop(), operands.pop(), operands.pop()));
                }
                operators.push(ch);
            }
        }

        while (!operators.isEmpty()) {
            operands.push(performOperation(operators.pop(), operands.pop(), operands.pop()));
        }

        return String.valueOf(operands.pop());
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '^') {
            return false;
        } else if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        } else {
            return true;
        }
    }

    private static double performOperation(char operator, double operand2, double operand1) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    throw new ArithmeticException("Division by zero");
                }
            case '^':
                return Math.pow(operand1, operand2);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }}
