package CodingTestStudy.BiggestNumber;

import java.util.*;

class Solution {
    public String solution(int[] numbers) {

        int len = numbers.length;
        String[]numbersStr = new String[len];
        for(int i=0; i<len; i++){
            numbersStr[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(numbersStr, (n1, n2)->(n2+n1).compareTo(n1+n2));
        StringBuilder sb = new StringBuilder();
        for(String num: numbersStr){
            sb.append(num);
        }
        while(sb.length()>1 && sb.charAt(0)=='0'){
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }
}