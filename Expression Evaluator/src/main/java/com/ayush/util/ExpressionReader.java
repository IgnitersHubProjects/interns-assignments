package com.ayush.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExpressionReader {

    public List<String> readExpressions(File file){
        List<String> expressionList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){

            String expression = null;
            while ((expression = bufferedReader.readLine()) != null){
                expression = expression.trim();
                expression = expression.substring(0, expression.length() - 1);
                expressionList.add(expression);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return expressionList;
    }
}
