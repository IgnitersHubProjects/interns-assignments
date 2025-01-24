import java.io.*;
import java.util.*;

public class ArithmeticExpressions 
{
    public static void main(String[] args) 
    {
        String inputFilePath = "C:\\Users\\LENOVO\\Desktop\\input.txt";
        String outputFilePath = "C:\\Users\\LENOVO\\Desktop\\output.txt";

        try 
        {
            List<String> lines = readFile(inputFilePath);
            List<String> results = new ArrayList<>();

            for (String line : lines) 
            {
                String expression = extractExpression(line);
                try 
                {
                    String processedExpression = preprocessExpression(expression);
                    double result = evaluateExpression(processedExpression);
                    results.add(line + " " + result);
                } 
                catch (Exception e) 
                {
                    results.add(line + " Error in expression");
                }
            }

            writeFile(outputFilePath, results);
            System.out.println("Results written to " + outputFilePath);
        } 
        catch (IOException e) 
        {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // reading
    private static List<String> readFile(String filePath) throws IOException 
    {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                lines.add(line);
            }
        }
        return lines;
    }

    // writing
    private static void writeFile(String filePath, List<String> lines) throws IOException 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) 
        {
            for (String line : lines) 
            {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    // extracting before '='
    private static String extractExpression(String line) 
    {
        int index = line.lastIndexOf('=');
        if (index != -1) 
        {
            return line.substring(0, index).trim();
        }
        return line.trim();
    }

    // for implicit multiplication
    private static String preprocessExpression(String expression) 
    {
        expression = expression.replaceAll("\\s+", ""); //removes all whitespaces
        expression = expression.replaceAll("\\)\\(", ")*("); 
        expression = expression.replaceAll("(\\d)\\(", "$1*("); 
        expression = expression.replaceAll("\\)(\\d)", ")*$1"); 
        return expression;
    }

    private static double evaluateExpression(String expression) 
    {
        return new Object() 
        {
            int pos = -1, ch;

            void nextChar() 
            {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            boolean eat(int charToEat) 
            {
                while (ch == ' ') nextChar();
                if (ch == charToEat) 
                {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() 
            {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() 
            {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() 
            {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() 
            {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) 
                { // parentheses
                    x = parseExpression();
                    eat(')');
                } 
                else if (eat('[')) 
                { // square brackets
                    x = parseExpression();
                    eat(']');
                } 
                else if (eat('{')) 
                { // curly braces
                    x = parseExpression();
                    eat('}');
                } 
                else if ((ch >= '0' && ch <= '9') || ch == '.') 
                { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } 
                else 
                {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation
                return x;
            }
        }.parse();
    }
}
