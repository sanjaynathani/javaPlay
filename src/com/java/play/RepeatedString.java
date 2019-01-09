package com.java.play;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class RepeatedString {

    static long repeatedString(String s, long n) {
        long countOfa = 0L;
        if(s.contains("a")) {
            countOfa = s.chars().filter(val -> ((char) val) == 'a' ).count();
            if(s.length() < n){
                countOfa = countOfa * (n/s.length());
                countOfa = countOfa + s.substring(0, Long.valueOf(n%s.length()).intValue()).chars().filter(val -> ((char) val) == 'a' ).count();
            }else{
                countOfa = countOfa + s.substring(0, Long.valueOf(n).intValue()).chars().filter(val -> ((char) val) == 'a' ).count();
            }

        }
        return countOfa;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        /*String s = scanner.nextLine();

        long n = scanner.nextLong();
        long result = repeatedString(s, n);

        scanner.close();

        System.out.println(result);*/
        System.out.println(3/2);
    }
}
