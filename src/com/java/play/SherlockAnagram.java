package com.java.play;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SherlockAnagram {

    public static void main(String[] args) {
        System.out.println(sherlockAndAnagrams("cdcd"));
    }

    static int sherlockAndAnagrams(String s) {
        Map<String, Integer> subStrMap = new HashMap<>();
        int noOfAnaGrams = 0;
        for(int i=0; i<s.length(); i++){
            for(int j=i; j<s.length(); j++){
                String subStr = s.substring(i, j+1);
                String sortedSubStr = subStr.chars().sorted().mapToObj(c -> String.valueOf((char)c)).collect(Collectors.joining());
                if(subStrMap.containsKey(sortedSubStr)){
                    noOfAnaGrams = noOfAnaGrams + subStrMap.get(sortedSubStr) ;
                    subStrMap.put(sortedSubStr, subStrMap.get(sortedSubStr)+1);
                }else{
                    subStrMap.put(sortedSubStr, 1);
                }
            }
        }
        return noOfAnaGrams;
    }

}
