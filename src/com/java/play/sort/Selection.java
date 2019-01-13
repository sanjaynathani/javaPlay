package com.java.play.sort;

import java.util.ArrayList;
import java.util.List;

public class Selection {

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
        int minIndex = 0;
        for(int i=0; i<list.size()-1; i++){
            minIndex = i;
            for(int j=i+1; j<list.size(); j++){
                if(list.get(j) < list.get(minIndex)){
                    minIndex = j;
                }
            }
            if(list.get(i) > list.get(minIndex)){
                int temp = list.get(i);
                list.set(i, list.get(minIndex));
                list.set(minIndex, temp);
            }
        }
    }
}
