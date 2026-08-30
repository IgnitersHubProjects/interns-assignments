package com.ayush.util;

import java.io.*;
import java.util.List;

public class ExpressionWriter {

    public void writeExpression(List<String> expressionList, File outputFile) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));

        for (String expression : expressionList) {
            bufferedWriter.write(String.format("%s\n", expression));
        }
        bufferedWriter.close();

    }

}
