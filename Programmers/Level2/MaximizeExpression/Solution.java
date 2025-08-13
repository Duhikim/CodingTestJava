package CodingTestStudy.Level2.MaximizeExpression;

import java.util.*;
import java.lang.Math;

class Solution {
    String[][] operators = new String[][]{
            {"*", "+", "-"},
            {"*", "-", "+"},
            {"+", "*", "-"},
            {"+", "-", "*"},
            {"-", "+", "*"},
            {"-", "*", "+"}
    };

    public long solution(String expression) {
        long answer = 0;

        for(String[] operator: operators){ // 6개
            List<String> exp = translate(expression);
            for(String op: operator){ // 3개
                for(int i=0; i<exp.size(); i++){ // 100개
                    String str = exp.get(i);
                    if(str.equals(op)){
                        long prev = Long.parseLong(exp.get(i-1));
                        long next = Long.parseLong(exp.get(i+1));
                        switch(op){
                            case "*": exp.set(i-1, Long.toString(prev*next)); break;
                            case "+": exp.set(i-1, Long.toString(prev+next)); break;
                            case "-": exp.set(i-1, Long.toString(prev-next)); break;
                        }
                        exp.remove(i+1);
                        exp.remove(i);
                        i--;
                    }
                }
            }

            answer = Math.max(answer, Math.abs(Long.parseLong(exp.get(0))));

        }
        return answer;
    }

    public List<String> translate(String expression){
        List<String> result = new ArrayList<>();

        int len = expression.length();
        int num = -1;

        for(int i=0; i<len; i++){
            char c = expression.charAt(i);
            if(c < '0' || c > '9') {
                if(num != -1) result.add(Integer.toString(num));
                num = -1;
                result.add("" + c);
            }
            else if(num == -1) num = (int)(c-'0');
            else {
                num *= 10;
                num += (int)(c-'0');
            }
            if(i == len-1) result.add(Integer.toString(num));
        }
        return result;
    }
}