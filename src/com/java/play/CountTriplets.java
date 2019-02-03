package com.java.play;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountTriplets {
    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(5L);
        list.add(5L);
        list.add(25L);
        list.add(125L);
        System.out.println(counter_1(list, 5));

        list.clear();
        list = new ArrayList<>();
        list.add(1L);
        list.add(3L);
        list.add(9L);
        list.add(9L);
        list.add(27L);
        list.add(81L);
        System.out.println(counter_1(list, 3));

        list.clear();
        list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(2L);
        list.add(4L);
        System.out.println(counter_1(list, 2));

        list.clear();
        list.add(1L);
        list.add(1L);
        list.add(1L);
        list.add(10L);
        list.add(10L);
        list.add(10L);
        System.out.println(counter_1(list, 1));
    }

    //2 - 3 - 1
    //3 - 4 - 3
    //4 - 5 - 6
    //5 - 6 - 10
    //6 - 7 - 15
    //7 - 8 - 21

    //2 - 3 - 1
    //

    static long counter(List<Long> list, long r) {
        Map<Long, Long> map = new HashMap<>();
        long noOfTriplet = 0;

        if(r == 1){
            list.stream().forEach(e -> map.compute(e, (k,v) -> v == null ? 1 : v +1));
            return map.keySet().stream().map(key -> (map.get(key))*(map.get(key) - 1)*(map.get(key) - 2) / 6).mapToLong(val -> val).sum();
        }
        for(long element : list){
            int count3 = 0;
            long val = element;
            long additional = 0;
            while(count3 < 3 && (val%r == 0 || val == 1)){
                if(val != element) {
                     if(!map.containsKey(val)) break;
                    additional = additional + map.get(val) - 1;
                }
                val = val / r;
                count3++;
            }
            if(count3 == 3) {
                noOfTriplet = noOfTriplet + additional + 1;
            }
            if(map.containsKey(element)){
                map.put(element, map.get(element)+1);
            }else{
                map.put(element, 1L);
            }
        }
        return noOfTriplet;
    }

    static long counter_1(List<Long> list, long r){
        Map<Long, Long> map1 = new HashMap<>();
        Map<Long, Long> map2 = new HashMap<>();
        long noOfTriplet = 0;

        for(Long element : list){
            if(map2.containsKey(element)){
                noOfTriplet = noOfTriplet + map2.get(element);
            }
            if(map1.containsKey(element)){
                if(map2.containsKey(element * r)){
                    map2.put(element * r, map2.get(element * r) + map1.get(element));
                }else{
                    map2.put(element * r, map1.get(element));
                }
            }
            if(map1.containsKey(element*r)){
                map1.put(element*r, map1.get(element * r) + 1);
            } else{
                map1.put(element*r, 1L);
            }
        }
        return  noOfTriplet;
    }

    static long counter_11(List<Long> list, long r){
        Map<Long,Long> v2 = new HashMap<>();
        Map<Long,Long> v3 = new HashMap<>();
        Long count = 0L;
        for (Long k:list) {
            count+=v3.get(k)==null?0:v3.get(k);
            if (v2.get(k)!=null) v3.compute(k*r,(key,value)->value!=null?value+v2.get(k):v2.get(k));
            v2.compute(k*r,(key,value)->value==null?1:value+1);
        }
        return count;
    }
    
}
