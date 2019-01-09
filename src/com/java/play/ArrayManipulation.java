package com.java.play;

import java.util.HashMap;
import java.util.Map;

public class ArrayManipulation {

    public static long manipulate(int n, int[][] queries){
        Map<Integer, Long> map = new HashMap<>();
        long maxNumber = 0;
        for(int row=0; row<queries.length; row++){
            for(int i=queries[row][0]; i<=queries[row][1]; i++){
                if(map.containsKey(i)){
                    map.put(i, map.get(i)+queries[row][2]);
                }else {
                    map.put(i, new Long(queries[row][2]));
                }
                if(map.get(i) > maxNumber){
                    maxNumber = map.get(i);
                }
            }
        }
        return maxNumber;
    }

    public static long manipulate_v1(int n, int[][] queries){
        Map<Integer, Long> map = new HashMap<>();
        long maxNumber = 0;
        int[] arr = new int[n];
        for(int row=0; row<queries.length; row++){
            arr[queries[row][0]-1] =- queries[row][2];
            arr[queries[row][1]-1] =+ queries[row][2];
        }
        int temp = 0;
        for(int i=0; i<n; i++){
            if(arr[i] > 0) temp = temp + arr[i];
            maxNumber = maxNumber > temp ? maxNumber : temp;
        }
        return maxNumber;
    }

    public static void main(String[] args){
        int[][] arr = {{1,2,100}, {2,5,100}, {3,4,100}};
        System.out.println(manipulate_v1(5, arr));
    }
}
