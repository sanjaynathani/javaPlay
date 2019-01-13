package com.java.play.sort;

import java.util.ArrayList;
import java.util.List;

public class Insertion {

    public static void main(String[] args) {
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

        sort(list);
        System.out.println(list);
    }

    public static void sort(List<Integer> list){
        for(int i=0; i<list.size()-1; i++){
            int j = i+1;
            while(j > 0 && list.get(j) < list.get(j-1)){
                int temp = list.get(j);
                list.set(j, list.get(j-1));
                list.set(j-1, temp);
                j--;
            }
        }
    }
}
