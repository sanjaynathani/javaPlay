package com.java.play;

public class FactTrailingZeros {

    public static long getTrailingZeros(long num){
        long zeros = 0;
        if(num >= 5) {
            long m = 5;
            while((num / m) > 0){
                zeros = zeros + (num / m);
                m = m * 5;
            }
        }
        return zeros;
    }

    public static void main(String[] args) {
        System.out.println(getTrailingZeros(200));
    }
}
