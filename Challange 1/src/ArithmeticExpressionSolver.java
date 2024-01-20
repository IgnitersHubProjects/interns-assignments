import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArithmeticExpressionSolver {

    public static void main(String[] args) {
        String inputFile = "input.txt";   // Change this to your input file path
        String outputFile = "output.txt"; // Change this to your output file path

        solveArithmeticExpressions(inputFile, outputFile);
    }

    private static void solveArithmeticExpressions(String inputFile, String outputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Trim leading and trailing whitespaces
                line = line.trim();

                if (!line.isEmpty()) {
                    // Evaluate and write the result to the output file
                    String result = evaluateExpression(line);
                    writer.write(line + " = " + result + System.lineSeparator());
                }
            }

            System.out.println("Expressions solved and results written to " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String evaluateExpression(String expression) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        try {
            // Using eval method to evaluate the expression
            Object result = engine.eval(expression);
            return result.toString();
        } catch (ScriptException e) {
            return "Error: " + e.getMessage();
        }
    }
}
