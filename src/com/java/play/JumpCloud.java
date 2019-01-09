package com.java.play;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JumpCloud {


    public static int jumpingOnClouds(int n, int[] arr){
        int jumps = 0;
        int i = 0;
        while(i < n-1){
            if(arr[i+2] == 0){
                i=i+2;
            }else if(arr[i+1] == 0){
                i=i+1;
            }else{
                jumps=-1;
                break;
            }
            jumps++;
        }
        return jumps;
    }

    public static void main(String[] args){
        int n=6;
        int[] arr = {0,0,0,0,1,0};
        System.out.println(jumpingOnClouds(n,arr));

        List<Long> values = new ArrayList<>();


        System.out.println(values.parallelStream().map(val -> val.toString()).collect(Collectors.joining(",")));
    }
}
