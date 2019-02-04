package com.java.play;

public class Factorial {

    public static void main(String[] args) {
        System.out.println(factIter(4l));
    }

    public static long fact(long num){
        if(num == 1){
            return num;
        }else{
            return num * fact(num-1);
        }
    }

    public static long factIter(long num){
        long result = 1;
        while(num != 1){
            result = result * num;
            num--;
        }
        return result;
    }
}
