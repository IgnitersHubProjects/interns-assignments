import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "input.txt"; // Input file with arithmetic expressions
        String outputFilePath = "output.txt"; // Output file for results

        try {
            List<String> expressions = readFromFile(inputFilePath);
            List<String> results = evaluateExpressions(expressions);
            writeToFile(outputFilePath, results);
            System.out.println("Arithmetic expressions have been evaluated. Check " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    
    private static List<String> readFromFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    
    private static List<String> evaluateExpressions(List<String> expressions) {
        List<String> results = new ArrayList<>();
        for (String expression : expressions) {
            try {
                double result = evaluate(expression);
                results.add(expression + " = " + result);
            } catch (Exception e) {
                results.add(expression + " = Error: " + e.getMessage());
            }
        }
        return results;
    }

    
    private static double evaluate(String expression) throws Exception {
        try {
           
            javax.script.ScriptEngineManager manager = new javax.script.ScriptEngineManager();
            javax.script.ScriptEngine engine = manager.getEngineByName("JavaScript");
            Object result = engine.eval(expression);
            return Double.parseDouble(result.toString());
        } catch (Exception e) {
            throw new Exception("Invalid expression");
        }
    }

    
    private static void writeToFile(String filePath, List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
