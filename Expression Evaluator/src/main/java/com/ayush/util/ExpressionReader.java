package com.ayush.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExpressionReader {

    public List<String> readExpressions(File file) throws IOException {
        List<String> expressionList = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String expression = null;
        while ((expression = bufferedReader.readLine()) != null){
            expression = expression.trim();
            expression = expression.substring(0, expression.length() - 1);
            expressionList.add(expression);
        }

        bufferedReader.close();
        return expressionList;
    }
}
