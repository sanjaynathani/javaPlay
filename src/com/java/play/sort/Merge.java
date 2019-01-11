package com.java.play.sort;

import java.util.ArrayList;
import java.util.List;

public class Merge {


    public static void main(String[] args){

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(3);
        list.add(7);

        list.add(2);
        list.add(6);
        list.add(4);

        list.add(15);
        list.add(1);
        list.add(9);

        System.out.println(list);

        System.out.println(devideAndMerge(list));
    }


    private static List<Integer> devideAndMerge(List<Integer> list){
        List<Integer> leftList = list.subList(0, list.size()/2);
        List<Integer> rightList = list.subList(list.size()/2, list.size());
        if(list.size() == 1){
            return  list;
        }
        if(leftList.size() > 1){
            leftList = devideAndMerge(leftList);
        }
        if(rightList.size() > 1){
            rightList = devideAndMerge(rightList);
        }
        int leftIndex = 0;
        int rightIndex = 0;
        List<Integer> sortedList = new ArrayList<>();
        while(leftIndex < leftList.size() && rightIndex < rightList.size()){
            if(leftList.get(leftIndex) > rightList.get(rightIndex)){
                sortedList.add(rightList.get(rightIndex));
                rightIndex++;
            }else{
                sortedList.add(leftList.get(leftIndex));
                leftIndex++;
            }
        }

        if(rightIndex < rightList.size()){
            sortedList.addAll(rightList.subList(rightIndex, rightList.size()));
        }else if(leftIndex < leftList.size()){
            sortedList.addAll(leftList.subList(leftIndex, leftList.size()));
        }
        return sortedList;
    }
}
