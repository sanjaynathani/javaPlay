package com.java.play.sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class FraudNotice {

    public static void main(String[] args) throws Exception{
        File file = new File("input.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        String inputStr[] = new String[2];
        int i = 0;
        while((line = reader.readLine()) != null){
            inputStr[i++] = line;
        }
        int d = Integer.parseInt(inputStr[0].split(" ")[1]);
        String[] strList = inputStr[1].split(" ");
        int[] expenditure = new int[strList.length];
        i = 0;
        for(String s : inputStr[1].split(" ")){
            expenditure[i++] = Integer.parseInt(s);
        }

        System.out.println(Calendar.getInstance().getTime());
        System.out.println(activityNotifications(expenditure, d));
        System.out.println(Calendar.getInstance().getTime());
    }

    static int activityNotifications(int[] expenditure, int d) {
        List<Integer> list = new ArrayList<Integer>();
        Map<Integer, Integer> map = new TreeMap<>();
        int medianVal = 0;
        boolean singleVal = (d % 2 != 0);
        int noticeCount = 0;

        for(int i = 0; i < (expenditure.length - d); i++) {
            // Crate sorted first time
            int midIndex = 0;
            if(i == 0) {
                int[] temp = Arrays.copyOfRange(expenditure, 0, d);
                System.out.println(temp[0]);
                System.out.println(temp[2]);
                System.out.println(temp[5]);
                list = new ArrayList(Arrays.stream(temp).boxed().sorted().collect(Collectors.toList()));
                for(int k=0; k<list.size(); k++){
                    map.putIfAbsent(list.get(k), k);
                }
                //System.out.println("Sorting Completed!!");
                System.out.println(map);
            }
            else {
                //Insert to sorted list
                int newVal = expenditure[d+i];
                int startIndex = 0;
                List<Integer> tempList = new ArrayList<>();
                if(newVal >= list.get(list.size()-1)) {
                    tempList.addAll(list.subList(1,list.size()));
                    tempList.add(newVal);
                    list = tempList;
                } else if(newVal <= list.get(1)) {
                    tempList.add(newVal);
                    tempList.addAll(list.subList(1,list.size()));
                    list = tempList;
                } else {
                    while(list.get(startIndex) < newVal) {
                        startIndex++;
                    }
                    //startIndex = binarySearchfForIndex(list, newVal, 1, list.size());
                    tempList.addAll(list.subList(1, startIndex+1));
                    tempList.add(newVal);
                    tempList.addAll(list.subList(startIndex+1, list.size()));
                    list = tempList;
                }
            }
            midIndex = (d/2);
            medianVal = singleVal ? list.get(midIndex) : (list.get(midIndex) + list.get(midIndex - 1) / 2);
            if(medianVal * 2 <= expenditure[d+i]) {
                noticeCount++;
            }
        }
        return noticeCount;
    }

    public static int binarySearchfForIndex(List<Integer> list, int value, int start, int end){
        int mid = (start+end)/2;
        if(value > list.get(mid-1)){
            return binarySearchfForIndex(list, value, mid+1, end);
        }else if(value < list.get(mid-1)){
            return binarySearchfForIndex(list, value, start, mid);
        }else {
            return mid;
        }
    }
}
