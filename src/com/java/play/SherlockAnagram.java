package com.java.play;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SherlockAnagram {

    public static void main(String[] args) throws Exception{
        //System.out.println(sherlockAndAnagrams("cdcd"));
        File file = new File("StrInput");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        System.out.println(isValid(reader.readLine()));
        reader.close();
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


    static String isValid(String s) {
        Map<Character, Integer> countMap = new HashMap<>();
        s.chars().mapToObj(c -> (char) c).forEach(c -> countMap.compute(c, (k,v) -> v == null ? 1 : v+1));
        // int valSum = countMap.values().stream().mapToInt(val -> val).sum();
        // if((valSum % s.length() == 0) || ((valSum - 1) % s.length() == 0)) return "YES";
        // else return "NO";

        Map<Integer, Integer> valCountMap = new HashMap<>();
        boolean changed = false;
        List<Integer> valList = new ArrayList<>(countMap.values());
        if(countMap.size() == 1) return "YES";
        System.out.println(countMap);
        int prevVal = valList.get(0);
        for(int i=1; i<valList.size()-1; i++){
            if(prevVal != valList.get(i) && prevVal != valList.get(i+1)){
                if(valList.get(i).intValue() == valList.get(i+1).intValue()){
                    if(valList.get(i) == 1){
                        return "NO";
                    }
                    changed = true;
                    if(prevVal == 1 || (prevVal-1) == valList.get(i)){
                        prevVal = valList.get(i);
                    }else{
                        return "NO";
                    }
                }else{
                    return "NO";
                }
                prevVal = valList.get(i);
            }else{
                if(prevVal != valList.get(i) && changed){
                    return "NO";
                }else if(prevVal != valList.get(i)){
                    if((valList.get(i) - 1) != prevVal && valList.get(i) != 1){
                        return "NO";
                    }
                    changed = true;
                }else if(valList.get(i) != prevVal){
                    return "NO";
                }
            }
        }
        int lastVal = valList.get(valList.size() - 1);
        if(lastVal == prevVal){
            return "YES";
        }else if(lastVal != prevVal && changed){
            return "NO";
        }else if(lastVal != prevVal && (lastVal == 1 || (lastVal - 1) == prevVal)){
            return "YES";
        }else{
            return "NO";
        }
    }

}
