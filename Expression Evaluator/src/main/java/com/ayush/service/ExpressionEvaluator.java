package com.ayush.service;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class ExpressionEvaluator {

    public String evaluateExpression(String input){
        try {
            Expression expression = new ExpressionBuilder(input).build();
            return Double.toString(expression.evaluate());
        }catch (Exception exception){
            return "invalid expression";
        }

    };
}

