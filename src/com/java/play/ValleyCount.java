package com.java.play;

public class ValleyCount {

    public static int getCount(int n, String[] steps){
        int count=0;
        int sum=0;
        String s = "test";
        if(s.charAt(0) == 'a'){

        }
        for(int i=0; i<n; i++){
            if(steps[i].equalsIgnoreCase("d")){
                sum--;
                if(sum == -1) count ++;
            }else if(steps[i].equalsIgnoreCase("u")){
                sum++;
            }
        }
        return count;
    }

    public static void main(String[] args){
        String[] steps = {"U","D","D","D","U","D","U","U"};
        System.out.println(getCount(8, steps));
    }
}
