package algo;

import java.util.HashSet;
import java.util.Set;

public class StringSubSeq {


    public static void main(String[] args) {
        String str = "abc";
        getSubSeq("",str);
    }

    private static void getSubSeq(String pre, String str){
        if(str == null || str.isEmpty()){
            return;
        }else{
            for(int i=0; i<str.length(); i++){
                String temp = pre + str.substring(i, i+1) ;
                permutation("", temp);
                getSubSeq(temp, str.substring(i+1, str.length()));
            }
        }
    }


    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) System.out.println(prefix);
        else {
            for (int i = 0; i < n; i++) {
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
            }
        }
    }
}
