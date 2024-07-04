package Task2;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Stack;

public class ArithmeticExpression {

    
    /** 
     * @param c
     * @return boolean
     */
    private static boolean isOpeningBracket(char c) {
        return c == '(' || c == '{' || c == '[';
    }

    
    /** 
     * @param c
     * @return boolean
     */
    private static boolean isClosingBracket(char c) {
        return c == ')' || c == '}' || c == ']';
    }

    /** 
     * @param c
     * @return boolean
     */
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    
    /** 
     * @param c
     * @return int
     */
    private static int precedence(char c) {
        if (c == '+' || c == '-') {
            return 1;
        } else if (c == '*' || c == '/') {
            return 2;
        } else if (c == '^') {
            return 3;
        } else {
            return -1;
        }
    }

    
    /** 
     * @param operands
     * @param operators
     */
    private static void calculate(Stack<Double> operands, Stack<Character> operators) {
        if (operands.size() < 2) {
            throw new RuntimeException("Invalid Expression");

        }
        char operator = operators.pop();
        double operand2 = operands.pop();
        double operand1 = operands.pop();

        if (operator == '+') {
            operands.push(operand1 + operand2);
        } else if (operator == '-') {
            operands.push(operand1 - operand2);
        } else if (operator == '*') {
            operands.push(operand1 * operand2);
        } else if (operator == '/') {
            if (operand2 == 0) {
                throw new ArithmeticException("Division by Zero");
            }
            operands.push(operand1 / operand2);
        } else if (operator == '^') {
            operands.push(Math.pow(operand1, operand2));
        } else {
            throw new IllegalArgumentException("Invalid Operator");
        }
    }

    
    /** 
     * @param expression
     * @return String
     */
    public static String solveExpression(String expression) {
        String result = null;

        Stack<Double> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        
        expression = expression.replaceAll("[\\s\\t]+", "")
                       .replaceAll("\\)\\(", ")*(")
                       .replaceAll("\\]\\[", ")*(")
                       .replaceAll("\\}\\{", ")*(")
                       .replaceAll("(\\d+)(\\(|\\[|\\{)", "$1*$2");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {

            char c = expression.charAt(i);
            if (c == ' ') {
                continue;
            } else if (Character.isDigit(c) || c == '.') {

                while (i < expression.length()
                        && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i));

                    i++;
                }
                i--;
                Double val = Double.parseDouble(sb.toString());
                operands.add(Double.parseDouble(sb.toString()));
                sb.setLength(0);
            } else if (isOpeningBracket(c)) {
                operators.add(c);
            } else if (isClosingBracket(c)) {
                while (!operators.isEmpty() && !isOpeningBracket(operators.peek())) {
                    calculate(operands, operators);
                }
                operators.pop();
            } else if (isOperator(c)) {

                if (c == '-' && (i == 0
                        || (isOperator(expression.charAt(i - 1)) || isOpeningBracket(expression.charAt(i - 1))))) {
                    sb.append(c);
                    continue;
                } else {
                    while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                        calculate(operands, operators);
                    }
                    operators.push(c);
                }

            } else {
                throw new IllegalArgumentException("Invalid Expression");
            }
        }
        while (!operators.isEmpty()) {
            calculate(operands, operators);
        }
        if (operands.isEmpty()) {
            return "Invalid Expression";
        }
        else
            return operands.pop().toString();

    }

    
    /** 
     * @param args
     */
    public static void main(String[] args) {

        String inputFile = "ArithmeticExpressionInput.txt";
        String outputFile = "ArithmeticExpressionOutput.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {

            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                if (inputLine.trim().isEmpty()) {
                    continue;
                }

                String[] line = inputLine.split("=");
                String expression = line[0].trim();
                String result = solveExpression(expression);
                

                String finalresult = expression + " = " + result;
                bufferedWriter.write(finalresult);
                bufferedWriter.newLine();
            }

            System.out.println("Expression Evaluated Succesfully.");

        } catch (IOException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
            
        }

    }

}
