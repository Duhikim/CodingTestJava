package CodingTestStudy.Level2.MakeSumsOfTwoQueueEqual;

import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {

        int answer = 0;

        Queue<Integer> queue1_ = new ArrayDeque<>();
        Queue<Integer> queue2_ = new ArrayDeque<>();
        long total1=0;
        long total2=0;
        long total =0;

        for(int i=0; i<queue1.length; i++){
            queue1_.add(queue1[i]);
            total1 += queue1[i];
            queue2_.add(queue2[i]);
            total2 += queue2[i];
        }
        total = total1+total2;
        if((total&1)==1) return -1;

        while(total1 != total2){
            if(total1 > total2){
                int extract = queue1_.poll();
                queue2_.add(extract);
                total1 -= extract;
                total2 += extract;
            }
            else{
                int extract = queue2_.poll();
                queue1_.add(extract);
                total2 -= extract;
                total1 += extract;
            }
            answer++;
            if(answer > 4*queue1.length) return -1;
        }

        return answer;
    }
}