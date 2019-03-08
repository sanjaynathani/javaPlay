package com.java.play;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExpressionEval {

    private static final Map<String, Integer> opMap;

    static {
        opMap = new HashMap<>();
        opMap.put("*" , 1);
        opMap.put("/" , 1);
        opMap.put("+" , 2);
        opMap.put("-" , 2);
        opMap.put("(" , 1000);
    }

    private static final String LEFT_PARENTH = "(";
    private static final String RIGHT_PARENTH = ")";

    public static void main(String[] args) {
        Integer val = evalExp("(1+2)/3*2");
        System.out.println(val);
    }

    static Integer evalExp(String exp){
        Stack<Integer> numStack = new Stack<>();
        Stack<String> opStack = new Stack<>();

        for(int i=0; i<exp.length(); i++){
            String c = new Character(exp.charAt(i)).toString();
            Integer num = getNum(c);
            if(num != null){
                numStack.push(num);
            } else if(c.equals(LEFT_PARENTH)){
                opStack.push(c);
            } else if(c.equals(RIGHT_PARENTH)){
                while(!opStack.peek().equals(LEFT_PARENTH)){
                    operation(numStack,opStack);
                }
                opStack.pop();
            } else {
                while(!opStack.isEmpty() && opMap.get(opStack.peek()) <= opMap.get(c)){
                    operation(numStack,opStack);
                }
                opStack.push(c);
            }
        }
        while(!opStack.isEmpty()){
            operation(numStack,opStack);
        }
        int result = numStack.pop();
        if(opStack.isEmpty() && numStack.isEmpty()){
            return result;
        }else{
            return null;
        }
    }

    private static Integer getNum(String c){
        try{
            return Integer.parseInt(c);
        }catch(Exception e){
            return null;
        }
    }

    private static void operation(Stack<Integer> numStack, Stack<String> opStack){
        String operand = opStack.pop();
        switch(operand) {
        case "+":
            numStack.push(numStack.pop() + numStack.pop());
            break;
        case "*":
            numStack.push(numStack.pop() * numStack.pop());
            break;
        case "/":
            int num2 = numStack.pop();
            int num1 = numStack.pop();
            numStack.push(num1/num2);
            break;
        }
    }
}
