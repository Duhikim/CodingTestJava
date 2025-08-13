package CodingTestStudy.Level2.CakeCutting;

import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;

        Map<Integer, Integer> toppingsA = new HashMap<>();
        Map<Integer, Integer> toppingsB = new HashMap<>();
        for(int top: topping){
            toppingsB.put(top, toppingsB.getOrDefault(top, 0)+1);
        }

        for(int i=0; i<topping.length-1; i++){
            int top = topping[i];

            toppingsA.put(top, toppingsA.getOrDefault(top, 0)+1);
            toppingsB.put(top, toppingsB.get(top)-1);
            if(toppingsB.get(top)==0) toppingsB.remove(top);

            if(toppingsA.size() == toppingsB.size()) answer++;
            else if(toppingsA.size() > toppingsB.size()) break;
        }

        return answer;
    }
}