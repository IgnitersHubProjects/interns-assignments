package Task1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ApproximateSearch {

    // Read strings from a file and return list of String
    public static List<String> getInputString(String fileName) throws FileNotFoundException, IOException {
        List<String> inpStrings = new ArrayList<String>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                inpStrings.add(currentLine.trim());
            }
        }
        return inpStrings;
    }
 
    // filter String based on a given pattern
    public static List<String> getOutputString(String pattern, List<String> inputString) {
        List<String> outputList = new ArrayList<String>();
        for (String s : inputString) {
            if (s.startsWith(pattern)) {
                outputList.add(s);
            }
        }
        return outputList;
    }

    public static void main(String[] args) {
        try {
            String filePath = "ApproximateSearchFile.txt";
            List<String> inputString = getInputString(filePath);
            Collections.sort(inputString);
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.print("Enter the string to be searched (Press Ctrl+C to exit): ");
                String pattern = sc.nextLine();
                List<String> outputList = getOutputString(pattern, inputString);

                if (outputList.isEmpty()) {
                    System.out.println("No matching string found");
                } else {
                    for (String s : outputList) {
                        System.out.println(s);
                    }
                }
                outputList.clear();
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not Found : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("File reading Error : " + e.getMessage());
        }
    }
}