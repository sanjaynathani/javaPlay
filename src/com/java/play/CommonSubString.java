package com.java.play;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CommonSubString {

    public static void main(String[] args) {

        System.out.println(twoStrings("A", "A"));
    }

    static Set<String> twoStrings(String s1, String s2) {
        Set<String> s1Set = new HashSet<>();
        for(int i=0; i<s1.length(); i++){
            for(int j=i+1; j<s1.length(); j++){
                s1Set.add(s1.substring(i, j));
            }
        }
        s1Set.remove(s1);

        Iterator<String> itr =  s1Set.iterator();

        while(itr.hasNext()){
            String s = itr.next();
            if(!s2.contains(s)){
                itr.remove();
            }
        }
        return s1Set;
    }

}
