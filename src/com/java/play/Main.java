package com.java.play;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        int[] input = {1,2,3};
        int target = 4;
        List<Integer> workList = new ArrayList<>();
        List<List<Integer>> output = new ArrayList<>();
        combineSum(input, workList, 0, target, output);
        System.out.println(output);
    }

    public static void combineSum(int[] input, List<Integer> workList, int index, int target, List<List<Integer>> output){
        if(target == 0){
            output.add(new ArrayList<>(workList));
            return;
        }else if(target < 0){
            return;
        }
        for(int i=index; i<input.length; i++){
            if(target >= input[i]){
                workList.add(input[i]);
                combineSum(input, workList, i, target - input[i], output);
                workList.remove(workList.size() - 1);
            }
        }
    }
}
