package com.java.play.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Heap {

    public static void main(String[] args) {
        int[] nums = {1,4,8,7,6,5,3,2};
        heapSort(nums, 2, 0);
    }

    public static void heapSort(int[] nums, int offSet, int replaceIndex){

        int size = nums.length - offSet;
        //build heap
        for(int i=(size/2)-1+offSet; i>=offSet; i--){
            buildHeap(nums, size, i, offSet);
        }

        for(int i=size-1+offSet; i>=offSet; i--){
            int temp = nums[i];
            nums[i] = nums[offSet];
            nums[offSet] = temp;
            buildHeap(nums, i, offSet, offSet);
        }

    }

    public static void buildHeap(int[] nums, int size, int index, int offSet){

        int largest = index;
        int left = (index*2 + 1) - offSet;
        int right = (index*2 + 2) - offSet;

        if(left < size && nums[left] > nums[largest]){
            largest = left;
        }

        if(right < size && nums[right] > nums[largest]){
            largest = right;
        }

        if(largest != index){
            int temp = nums[index];
            nums[index] = nums[largest];
            nums[largest] = temp;
            buildHeap(nums, size, largest, offSet);
        }
    }
}
