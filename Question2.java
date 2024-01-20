package ignitershub.assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Question2 {

    public static String solveExpression(String expression) {
        try {
            double result = evaluateExpression(expression);
            return String.valueOf(result);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private static double evaluateExpression(String expression) {
        // You may need to implement your own expression evaluation logic here
        return Double.parseDouble(expression); // Example: assumes expressions are valid and only contain numbers
    }

    public static void processFile(String inputFile, String outputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    String result = solveExpression(line);
                    writer.write(result);
                    writer.newLine();
                }
            }

            System.out.println("Expressions solved and results written to " + outputFile);

        } catch (IOException e) {
            System.err.println("Error reading/writing file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String inputFile = "E:\\input.txt";    // Change this to the path of your input file
        String outputFile = "E:\\output.txt";  // Change this to the desired output file path

        processFile(inputFile, outputFile);
    }
}

