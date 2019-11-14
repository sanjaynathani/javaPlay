package com.java.play.sort;

import java.util.ArrayList;
import java.util.List;

public class Quick {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(80);
        list.add(30);
        list.add(90);
        list.add(40);
        list.add(50);
        list.add(70);

        sort(list, list.size() / 2);

        System.out.println(list);
    }

    private static void sort(List<Integer> list,int pivot){
        if(list.size() == 0) return;

        int left = 0;
        int right = list.size() - 1;
        System.out.println("Pivot ="+pivot);
        while(left < right){

            if(list.get(left) > list.get(right)){
                int temp = list.get(left);
                list.set(left, list.get(right));
                list.set(right, temp);
                if(left == pivot) pivot = right;
                if(right == pivot) pivot = left;
            }

            if(list.get(left) <= list.get(pivot)){
                left++;
            }

            if(list.get(right) >= list.get(pivot)){
                right--;
            }
        }

        //if(pivot > 0) {
            List<Integer> leftList = list.subList(0, pivot);
        //System.out.println("Left List ="+leftList);
          //  if (leftList.size() > 0) {
                sort(leftList, leftList.size() / 2);
            //}
        //}

        //if(pivot < list.size()) {
            List<Integer> rightList = list.subList(pivot+1, list.size());
        //System.out.println("Right List ="+rightList);
          //  if (rightList.size() > 0) {
                sort(rightList, rightList.size() / 2);
            //}
        //}

        //list.clear();
        //list.addAll(leftList);
        //list.addAll(rightList);
    }

}