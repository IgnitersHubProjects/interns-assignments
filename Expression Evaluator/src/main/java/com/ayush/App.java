package com.ayush;

import com.ayush.service.ExpressionEvaluator;
import com.ayush.util.ExpressionReader;
import com.ayush.util.ExpressionWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        File inputFile = new File("Expression Evaluator/src/data/input.txt");

        ExpressionReader expressionReader = new ExpressionReader();
        List<String> expressionList =  expressionReader.readExpressions(inputFile);

        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
        List<String> ansList = new ArrayList<>();

        for (String expression : expressionList){

            String result = expressionEvaluator.evaluateExpression(expression);
            ansList.add(String.format("%s = %s", expression, result));
        }

        File outputFile = new File("Expression Evaluator/src/data/output.txt");

        ExpressionWriter expressionWriter = new ExpressionWriter();
        expressionWriter.writeExpression(ansList, outputFile);


    }
}
