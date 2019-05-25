package com.java.play;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    static int sockMerchant(int n, int[] ar) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int noOfPair = 0;
        for(int i=0; i<n; i++) {
            if(countMap.containsKey(ar[i])){
                countMap.put(ar[i], countMap.get(ar[i])+1);
            }else{
                countMap.put(ar[i], 1);
            }
        }
        for(int key : countMap.keySet()) {
            noOfPair = noOfPair + (countMap.get(key)/2);
        }

        /*Set<Map.Entry<int[], Long>> entrySet = Stream.of(ar).collect(Collectors.groupingBy(el -> el, Collectors.counting())).entrySet();
        Long val = entrySet.stream().map(entry -> entry.getValue()).reduce((val1, val2) -> (val1/2) + (val2/2)).orElse(0L);*/

        return noOfPair;
    }


    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        //System.out.println("TEST "+ (0-(-3)));
        /*String s = null + "test";
        System.out.println(s);
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(n, ar);
        System.out.println(result);*/

        /*bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();*/

        //scanner.close();
        //printFib(5);

       /* Solution solution = new Solution();
        int[] nums = {2,11,1,10,4,10,1,4,2};
        int k = 3;
        System.out.println(solution.canPartitionKSubsets_1(nums, k));*/

        String str = "efdac4d23ad999668b9740612da3aa8eZExplanation_No_Check_(Client_4886844_-_\\u8A31_\\u6BD3\\u5A77).pdf";
        //System.out.println(str);
        //System.out.println(str.replaceAll("\\", ""));
        System.out.println(true && false && true || true && true);
    }


    public static void printFib(int num){
        if(num == 0) return;
        printFib(num - 1);
        System.out.print(num + " ");
    }
}



class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        ReturnVal returnVal = getSortedList(nums);
        print(returnVal.root);
        if(isSubSetPossible(returnVal.root, returnVal.lastNode, returnVal.sum/k)){
            return true;
        }
        return false;
    }


    public boolean isSubSetPossible(Node start, Node end, int sumValue){
        if(start == null && end == null){
            return true;
        }else if(start == null){
            if(end.value == sumValue) return true;
            else return false;
        }else if(end == null){
            if(start.value == sumValue) return true;
            else return false;
        }else{
            Node tempStart = start;
            int sum = end.value;
            while(tempStart != null && sum < sumValue){
                sum = sum + tempStart.value;
                tempStart = tempStart.next;
            }

            if(sum == sumValue){
                //Delete all nodes from start to tempStart
                if(sum != end.value){
                    start = tempStart == null ? null : tempStart;
                    if(start != null) start.prev = null;
                }
                if(start != null && start.equals(end)) {
                    end = null;
                    start = null;
                }
                else{
                    end = end.prev;
                    if(end != null) end.next = null;
                }
            }else{
                // Keep from start until sum matches
                Node start1 = start;
                while(start1 != null && !start1.equals(tempStart) && sum != sumValue){
                    sum = sum - start1.value;
                    if(sum < sumValue && tempStart != null) {
                        sum = sum + tempStart.value;
                        tempStart = tempStart.next;
                    }
                    if(sum != sumValue) start1 = start1.next;
                }
                if(sum != sumValue) return false;
                else{
                    start1.next = tempStart;
                    tempStart.prev = start1;
                    //start = start1;
                    end = end.prev;
                    end.next = null;
                }
            }
            //print(start);
            //System.out.println("START :: "+start.value);
            //System.out.println("END :: "+end.value);
            return isSubSetPossible(start,end,sumValue);
        }
    }

    public void print(Node root){
        while(root != null) {
            System.out.print(root.value +"->");
            root = root.next;
        }
        System.out.println();
    }

    public ReturnVal getSortedList(int[] nums){
        Node root = new Node(nums[0]);
        Node lastNode = root;
        int sum = nums[0];
        System.out.println("START :: "+root.value);
        System.out.println("END :: "+lastNode.value);
        for(int i=1; i<nums.length; i++){
            Node temp = lastNode;
            while(temp.prev != null && temp.value > nums[i]){
                temp = temp.prev;
            }
            Node newNode = new Node(nums[i]);
            if(temp.prev == null){
                if(temp.value > nums[i]){
                    newNode.next = temp;
                    temp.prev = newNode;
                    root = newNode;
                }else{
                    newNode.next = temp.next;
                    if(temp.next != null) temp.next.prev = newNode;
                    newNode.prev = temp;
                    temp.next = newNode;
                    if(newNode.prev.equals(lastNode)) lastNode = newNode;
                }
            }else if(temp.next != null){
                Node temp1 = temp.next;
                temp.next = newNode;
                newNode.next = temp1;
                newNode.prev = temp;
                if(temp1.prev != null)temp1.prev = newNode;
            }else{
                temp.next = newNode;
                newNode.prev = temp;
                lastNode = newNode;
            }
            System.out.println("START :: "+root.value);
            System.out.println("END :: "+lastNode.value);
            sum = sum + nums[i];
        }
        return new ReturnVal(root, lastNode, sum);
    }


    public boolean search(int[] groups, int row, int[] nums, int target) {
        if (row < 0) return true;
        int v = nums[row--];
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + v <= target) {
                groups[i] += v;
                if (search(groups, row, nums, target)) return true;
                groups[i] -= v;
            }
            if (groups[i] == 0) break;
        }
        return false;
    }

    public boolean canPartitionKSubsets_1(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;
        int target = sum / k;

        Arrays.sort(nums);
        int row = nums.length - 1;
        if (nums[row] > target) return false;
        while (row >= 0 && nums[row] == target) {
            row--;
            k--;
        }
        return search(new int[k], row, nums, target);
    }
}

class Node {
    public int value;
    public Node next;
    public Node prev;
    public Node(int value){
        this.value = value;
    }
}

class ReturnVal {
    public Node root;
    public Node lastNode;
    public int sum;

    public ReturnVal(Node root, Node lastNode, int sum){
        this.root = root;
        this.lastNode = lastNode;
        this.sum = sum;
    }
}
