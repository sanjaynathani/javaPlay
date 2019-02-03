package com.java.play;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

public class CountFreq {

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/home/sanjay/work/freqInput.txt")))) {

            int q = Integer.parseInt(bufferedReader.readLine().trim());
            List<int[]> queries = new ArrayList<>(q);
            Pattern p  = Pattern.compile("^(\\d+)\\s+(\\d+)\\s*$");
            for (int i = 0; i < q; i++) {
                int[] query = new int[2];
                Matcher m = p.matcher(bufferedReader.readLine());
                if (m.matches()) {
                    query[0] = Integer.parseInt(m.group(1));
                    query[1] = Integer.parseInt(m.group(2));
                    queries.add(query);
                }
            }
            List<Integer> ans = freqQuery_1(queries);
            System.out.println(ans);
        }
    }

    /*static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> resultList = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(List<Integer> query : queries){
            if(query.get(0) == 1){
                map.compute(query.get(1), (k,v) -> v == null ? 1 : v+1);
            }else if(query.get(0) == 2){
                map.compute(query.get(1), (k,v) -> v == null ? 0 : v-1);
            }else if(query.get(0) == 3){
                Set<Integer> set = new HashSet<>();
                set.addAll(map.values());
                if(set.contains(query.get(1))){
                    resultList.add(1);
                }else{
                    resultList.add(0);
                }
            }
        }
        return resultList;
    }*/

    /*static List<Integer> freqQuery(List<int[]> queries) {
        List<Integer> resultList = new ArrayList<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Set<Integer>> freqMap = new HashMap<>();
        for(int[] query : queries){
            int opNum = query[0];
            int valNum = query[1];
            if(opNum == 1){
                if(!countMap.containsKey(valNum)){
                    countMap.put(valNum, 1);
                    freqMap.computeIfAbsent(countMap.get(valNum), k -> new HashSet<>()).add(valNum);
                }else {
                    if(freqMap.get(countMap.get(valNum)) != null && freqMap.get(countMap.get(valNum)).contains(valNum)){
                        freqMap.get(countMap.get(valNum)).remove(valNum);
                    }
                    countMap.put(valNum, countMap.get(valNum) + 1);
                    freqMap.computeIfAbsent(countMap.get(valNum), k -> new HashSet<>()).add(valNum);
                }
            }else if(opNum == 2){
                if(freqMap.get(countMap.get(valNum)) != null && freqMap.get(countMap.get(valNum)).contains(valNum)){
                    freqMap.get(countMap.get(valNum)).remove(valNum);
                }
                countMap.computeIfPresent(valNum, (k,v) -> v - 1);
            }else if(opNum == 3){
                if(freqMap.get(valNum) != null && freqMap.get(valNum).size() > 0){
                    resultList.add(1);
                }else {
                    resultList.add(0);
                }
                //countMap.clear();
                //freqMap.clear();
            }
        }
        return resultList;
    }*/

    static List<Integer> freqQuery(List<int[]> queries) {
        List<Integer> resultList = new ArrayList<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int[] query : queries){
            int opNum = query[0];
            int valNum = query[1];
            if(opNum == 1){
                if(!countMap.containsKey(valNum)){
                    countMap.put(valNum, 1);
                    freqMap.compute(countMap.get(valNum), (k,v) -> v == null || v == 0 ? 1 : v+1);
                }else {
                    freqMap.computeIfPresent(countMap.get(valNum), (k,v) -> v == 0 ? 0 : v-1);
                    if(freqMap.get(countMap.get(valNum)) == 0){
                        freqMap.remove(countMap.get(valNum));
                    }
                    countMap.put(valNum, countMap.get(valNum) + 1);
                    freqMap.compute(countMap.get(valNum), (k,v) -> v == null || v == 0 ? 1: v + 1);
                }
            }else if(opNum == 2){
                freqMap.computeIfPresent(countMap.get(valNum), (k,v) -> v == 0 ? v : v - 1);
                if(freqMap.get(countMap.get(valNum)) == 0){
                    freqMap.remove(countMap.get(valNum));
                }
                countMap.computeIfPresent(valNum, (k,v) -> v - 1);
                if(countMap.get(valNum) == 0){
                    countMap.remove(valNum);
                }
                freqMap.compute(countMap.get(valNum), (k,v) -> v == null || v == 0 ? 1 : v + 1);
            }else if(opNum == 3){
                if(freqMap.get(valNum) != null && (freqMap.get(valNum) > 0 || (freqMap.get(valNum) == 0 && valNum == 0))) {
                    resultList.add(1);
                }else {
                    resultList.add(0);
                }
            }
        }
        return resultList;
    }

}
