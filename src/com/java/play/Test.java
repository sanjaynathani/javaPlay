package com.java.play;

import java.util.BitSet;

public class Test {

    public static void main(String[] args) {
        BitSet bitSet = new BitSet(6);

        for(int i=0; i<5; i++){
            bitSet.set(i);
        }

        System.out.println(bitSet.cardinality());
    }

}
