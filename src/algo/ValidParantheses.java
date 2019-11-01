package algo;

import java.util.ArrayList;
import java.util.List;

public class ValidParantheses {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int n = 2;
        //generate(n, n, n, "", list);
        generateParenthesis(list, "", n, n, n);
        System.out.println(list);
    }

    public static void generate(int n, int left, int right, String s, List<String> result){
        if(left == 0 && right == 0){
            result.add(s);
        }else{
            if(left > 0){
                left = left -1;
                s = s + "(";
                generate(n, left, right, s, result);
            }
            if(right > 0 && left < right){
                right = right - 1;
                s = s + ")";
                generate(n, left, right, s, result);
            }
        }
    }

    public static void generateParenthesis(List<String>list, String perentheses, int n, int left, int right) {
        //if all parentheses are used
        if (left==0 && right==0)list.add(perentheses);
        else {
            //if not all ( are used, we can easily add one more (
            if (left>0)generateParenthesis(list, perentheses+"(", n, left-1, right);
            //we are not allowed to add ) if our string for now is valid perentheses string
            if (left<right && right>0)generateParenthesis(list, perentheses+")", n, left, right-1);
        }
    }
}
