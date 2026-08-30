package com.ayush.util;

import java.io.*;
import java.util.List;

public class ExpressionWriter {

    public void writeExpression(List<String> expressionList, File outputFile) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))){

            for (String expression : expressionList) {
                bufferedWriter.write(String.format("%s\n", expression));
            }
        }catch (FileNotFoundException fileNotFoundException){
            System.out.printf("%s\n", fileNotFoundException.getMessage());
        }catch (IOException ioException){
            System.out.printf("%s\n", ioException.getMessage());
        }

    }

}
