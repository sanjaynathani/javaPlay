package com.java.play;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class FirstUnique {

    static int returnFirstUnique(String str){
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> indexMap = new HashMap<>();
        for(int i=0; i<str.length(); i++){
            Character c = str.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }else{
                map.put(c, 1);
            }

            if(!indexMap.containsKey(c)){
                indexMap.put(c, i);
            }
        }
        int uniqueIndex = ' ';
        for(Character c : map.keySet()){
            if(map.get(c) == 1){
                uniqueIndex = indexMap.get(c);
            }
        }
        System.out.println(indexMap);
        return uniqueIndex;
    }

    // Iterate string from end (thanks for hint!!)
    // Logic here is to maintain value of top of stack unique
    static int returnFirstUniqueWithStack(String str){
        Map<Character, Integer> indexMap = new HashMap<>();
        Stack<Character> stack = new Stack<>();
        for(int i=str.length()-1; i>=0; i--){
            Character c = str.charAt(i);
            if(!indexMap.containsKey(c)){
                indexMap.put(c, i);
                stack.push(c);
            }else{
                if(!stack.isEmpty() && stack.peek().equals(c)){
                    stack.pop();
                }
            }
        }
        if(stack.isEmpty()) return -1;
        return indexMap.get(stack.peek());
    }

    // Iterate string from start
    // Logic here is to maintain value at head of queue
    static int returnFirstUniqueWithQueue(String str){
        Map<Character, Integer> indexMap = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();
        for(int i=0; i<str.length(); i++){
            Character c = str.charAt(i);
            if(!indexMap.containsKey(c)){
                indexMap.put(c, i);
                queue.add(c);
            }else{
                if(!queue.isEmpty() && queue.peek().equals(c)){
                    queue.poll();
                }
            }
        }
        if(queue.isEmpty()) return -1;
        return indexMap.get(queue.peek());
    }

    public static void main(String[] args) {
        System.out.println(returnFirstUniqueWithQueue("conclusion"));
    }

}