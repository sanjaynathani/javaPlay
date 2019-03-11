package com.java.play;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordSynonym {

    public static void main(String[] args) {
        String str = "This is test is to count word word";
        System.out.println(countWords(str));

        String[][] synArray = {{"cold", "cool"}, {"chill", "cool"}, {"hot", "warm"}, {"hot", "sizzling"},
                               {"cold", "freeze"}, {"rain", "drizzling"}, {"drop", "drizzling"}, {"fire", "sizzling"}};

        System.out.println(synonymMap(synArray));
    }

    public static Map<String, Integer> countWords(String sentence){
        String[] strArray = sentence.split(" ");
        Map<String, Integer> countMap = new HashMap<>();
        for(String str : strArray){
            countMap.compute(str, (k,v)->v == null ? 1 : v + 1);
        }
        return countMap;
    }

    public static Map<String, Set<String>> synonymMap(String[][] synArray){
        Map<String, Set<String>> synMap = new HashMap<>();
        for(String[] synPair : synArray){
            if(synMap.containsKey(synPair[0])){
                if(!synMap.get(synPair[0]).contains(synPair[1])){
                    synMap.get(synPair[0]).add(synPair[1]);
                }
            } else if(synMap.containsKey(synPair[1])) {
                if(!synMap.get(synPair[1]).contains(synPair[0])){
                    synMap.get(synPair[1]).add(synPair[0]);
                }
            } else if(!synMap.isEmpty()){
                boolean found = false;
                for(String key : synMap.keySet()){
                    if(synMap.get(key).contains(synPair[0]) || synMap.get(key).contains(synPair[1])){
                        if(!synMap.get(key).contains(synPair[0])){
                            synMap.get(key).add(synPair[0]);
                        }
                        if(!synMap.get(key).contains(synPair[1])){
                            synMap.get(key).add(synPair[1]);
                        }
                        found = true;
                        break;
                    }
                }
                if(!found){
                    Set<String> synSet = new HashSet<>();
                    synSet.add(synPair[1]);
                    synMap.put(synPair[0], synSet);
                }
            } else {
                Set<String> synSet = new HashSet<>();
                synSet.add(synPair[1]);
                synMap.put(synPair[0], synSet);
            }
        }
        return synMap;
    }

}
