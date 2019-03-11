package com.java.play;

import java.util.LinkedList;
import java.util.Queue;

public class AllSubSeqs {

    static void printAllSubSeq(String str, int start, int end, String subStr){

        if(start == end){
            System.out.println(subStr);
        }

        for(int i=start; i<str.length(); i++){
            subStr = str.substring(start, end);
            printAllSubSeq(str, start+1, end, subStr);
        }
    }

    static void printAllIterative(String str){
        Queue<Character> queue = new LinkedList<>();
        int ct = 0;
        queue.add(str.charAt(ct++));

        while(!queue.isEmpty()){
            Character c = queue.poll();
            System.out.print(c);
            for(int i=1; i<str.length(); i++){
                for(int j=i+1; j<=str.length(); j++){
                    String sub = str.substring(0,j);
                    System.out.println(sub);
                }
            }
            if(ct < str.length())
                queue.add(str.charAt(ct++));
        }

    }

    public static void main(String[] args) {
        String str = "abc";

        printAllSubSeq(str, 0, str.length(), "");
        //printAllIterative(str);
    }
}
