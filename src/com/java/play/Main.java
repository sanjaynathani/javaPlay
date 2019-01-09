package com.java.play;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    static int sockMerchant(int n, int[] ar) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int noOfPair = 0;
        for(int i=0; i<n; i++) {
            if(countMap.containsKey(ar[i])){
                countMap.put(ar[i], countMap.get(ar[i])+1);
            }else{
                countMap.put(ar[i], 1);
            }
        }
        for(int key : countMap.keySet()) {
            noOfPair = noOfPair + (countMap.get(key)/2);
        }

        /*Set<Map.Entry<int[], Long>> entrySet = Stream.of(ar).collect(Collectors.groupingBy(el -> el, Collectors.counting())).entrySet();
        Long val = entrySet.stream().map(entry -> entry.getValue()).reduce((val1, val2) -> (val1/2) + (val2/2)).orElse(0L);*/

        return noOfPair;
    }


    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        String s = null + "test";
        System.out.println(s);
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(n, ar);
        System.out.println(result);

        /*bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();*/

        scanner.close();
    }
}
