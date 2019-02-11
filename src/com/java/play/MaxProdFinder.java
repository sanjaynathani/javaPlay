package com.java.play;

import java.util.ArrayList;
import java.util.List;

public class MaxProdFinder {

    public static long maxProduct(List<Long> list, int n){
        long max = 0;

        for(int i=0; i<list.size()-2; i++){
            for(int j=i+1; j<list.size()-1; j++){
                int k = j+1;
                System.out.println(i +" "+ j +" " +k);
                long product = list.get(i)*list.get(j)*list.get(k);
                if(product > max){
                    max = product;
                }
            }
        }
        return  max;
    }

    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(4L);
        list.add(3L);
        list.add(-6L);
        list.add(-7L);
        list.add(0L);
        System.out.println(maxProduct(list, 3));
    }
}
