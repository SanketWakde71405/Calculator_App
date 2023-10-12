package com.example.calculator;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class BODMASCalculator {
    private static final Map<String, Integer> PRECEDENCE = new HashMap<>();
    static {
        PRECEDENCE.put("+", 1);
        PRECEDENCE.put("-", 1);
        PRECEDENCE.put("*", 2);
        PRECEDENCE.put("÷", 2);
        PRECEDENCE.put("%",2);
        PRECEDENCE.put("^", 3);
        PRECEDENCE.put("√",3);
        PRECEDENCE.put("!",3);
        PRECEDENCE.put("sin", 4);
        PRECEDENCE.put("cos", 4);
        PRECEDENCE.put("tan", 4);
        PRECEDENCE.put("ln", 4);
        PRECEDENCE.put("abs",4);
        PRECEDENCE.put("asin",4);
        PRECEDENCE.put("acos",4);
        PRECEDENCE.put("atan",4);
        PRECEDENCE.put("sqrt", 4);
    }

    public static String getAngleMode() {
        return angleMode;
    }

    public static void setAngleMode(String angleMode) {
        BODMASCalculator.angleMode = angleMode;
    }

    private static String angleMode;


    public static String  evaluateExpression(String expression) {
        if (expression.equals("")){
            return "0";
        }
        List<String> postfix = convertToPostfix(expression);
        return evaluatePostfix(postfix);
    }

    private static List<String> convertToPostfix(String expression) {
        expression = expression.replaceAll("\\s", ""); // Remove spaces
        expression = expression.replace("π", String.valueOf(Math.PI));
        expression =expression.replace("e",String.valueOf(Math.E));
        List<String> postfix = new ArrayList<>();
        Stack<String> operators = new Stack<>();
        ArrayList<String> error= new ArrayList<>();
        error.add("Error");

        int i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    num.append(expression.charAt(i));
                    i++;
                }
                postfix.add(num.toString());
            } else if (Character.isLetter(c)) {
                StringBuilder func = new StringBuilder();
                while (i < expression.length() && Character.isLetter(expression.charAt(i))) {
                    func.append(expression.charAt(i));
                    i++;
                }
                operators.push(func.toString());
            } else if (isOperator(String.valueOf(c))) {
                while (!operators.isEmpty() && (isOperator(operators.peek()) ||
                       isFunction(operators.peek()))&& PRECEDENCE.get(String.valueOf(c)) <= PRECEDENCE.get(operators.peek())) {
                    postfix.add(operators.pop());
                }
                operators.push(String.valueOf(c));
                i++;
            }else if(c=='!'){
                operators.push("!");
                i++;
            }
            else if(c=='√') {
                operators.push("√");
                i++;
            } else if (c == '(') {
                operators.push("(");
                i++;
            } else if (c == ')') {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    postfix.add(operators.pop());
                }
                if (!operators.isEmpty() && operators.peek().equals("(")) {
                    operators.pop();
                } else {

                    return error;
                }
                i++;
            } else {
                return error;
            }
        }

        while (!operators.isEmpty()) {
            if (operators.peek().equals("(")) {
                return error;
            }
            postfix.add(operators.pop());

        }

        return postfix;
    }

    private static String evaluatePostfix(List<String> postfix) {
        Stack<Double> stack = new Stack<>();
        for (String token : postfix) {
            if (isOperator(token)) {
                if (stack.size() < 2) {
                    if (stack.size()==1 && token.equals("-")){
                        double res=stack.pop();
                        stack.push(-1*res);
                        continue;
                    }else if (stack.size()==1 && token.equals("+")){
                        double res=stack.pop();
                        stack.push(res);
                        continue;
                    }else if(stack.size()==1&& token.equals("!")){
                        double res=stack.pop();
                        stack.push(FactorialCalculator(res));
                    }else{
                        return "Error";
                    }

                }
                double b = stack.pop();
                double a = stack.pop();
                if (applyOperator(a,b,token).equals("Error")){
                    return "Error";
                }
                double result = Double.parseDouble(applyOperator(a, b, token));
                stack.push(result);
            } else if (PRECEDENCE.containsKey(token)) {
                if (stack.size() < 1) {
                    return "Error";
                }
                double arg = stack.pop();
                if (applyFunction(token,arg).equals("Error")){
                    return "Error";
                }
                double result = Double.parseDouble(applyFunction(token, arg));
                stack.push(result);
            } else {
                if (token.equals("Error")){
                    return token;
                }
                stack.push(Double.parseDouble(token));
            }
        }

        if (stack.size() != 1) {
            return "Error";
        }

        return stack.pop().toString();
    }

    private static boolean isOperator(String token) {
        return "+-*÷^%".contains(token);
    }
    private static boolean isFunction(String token){
        return token.equals("!") || token.equals("sin") || token.equals("cos") || token.equals("tan") ||
                token.equals("asin") || token.equals("acos") || token.equals("atan") || token.equals("ln") ||
                token.equals("abs") || token.equals("√");
    }

    private static String applyOperator(double a, double b, String operator) {
        switch (operator) {
            case "+":
                return Double.toString(a + b);
            case "-":
                return Double.toString(a - b);
            case "*":
                return Double.toString(a * b);
            case "÷":
                if (b == 0) {
                    return "Error";
                }
                return Double.toString(a / b);
            case "^":
                return Double.toString(Math.pow(a, b));
            case "%":
                return Double.toString(a%b);
            default:
                return "Error";
        }
    }

    private static double FactorialCalculator(double arg){
        if (arg==0){
            return 1;
        }
        return arg*FactorialCalculator(arg-1);
    }

    private static String applyFunction(String functionName, double argument) {
        switch (functionName) {
            case "sin":
                if (getAngleMode().equals("deg")){
                    return Double.toString(Math.sin(Math.toRadians(argument)));
                }
                return Double.toString(Math.sin(argument));
            case "cos":
                if (getAngleMode().equals("deg")){
                    return Double.toString(Math.cos(Math.toRadians(argument)));
                }
                return String.valueOf(Math.cos(argument));
            case "tan":
                if (getAngleMode().equals("deg")){
                    if (argument%90==0){
                        return String.valueOf(Double.POSITIVE_INFINITY);
                    }
                    return String.valueOf(Math.tan(Math.toRadians(argument)));
                }
                else if ((argument%(Math.PI/2))==0){
                    return String.valueOf(Double.POSITIVE_INFINITY);
                }
                return String.valueOf(Math.tan(argument));
            case "asin":
                if (getAngleMode().equals("deg")){
                    return String.valueOf((Math.asin(argument)/Math.PI)*180);
                }
                return String.valueOf(Math.asin(argument));
            case "acos":
                if (getAngleMode().equals("deg")){
                    return String.valueOf((Math.acos(argument)/Math.PI)*180);
                }
                return String.valueOf(Math.acos(argument));
            case "atan":
                if (getAngleMode().equals("deg")){
                    return String.valueOf((Math.atan(argument)/3.141593)*180);
                }
                return String.valueOf(Math.atan(argument));
            case "ln":
                return String.valueOf(Math.log(argument));
            case "√":
                return String.valueOf(Math.sqrt(argument));
            case "abs":
                return String.valueOf(Math.abs(argument));
            case "!":
                return String.valueOf(FactorialCalculator(argument));
            default:
                return "Error";
        }
    }

}
