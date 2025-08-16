package CodingTestStudy.Level2.HIndex;

import java.util.*;
import java.lang.Math;
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        int len = citations.length;
        Arrays.sort(citations);
        for(int i=0; i<len; i++){
            if(len-i == citations[i]) return citations[i];
            if(len-i < citations[i]) {
                answer = Math.max(answer, len-i);
            } else{
                answer = Math.max(answer, citations[i]);
            }
        }

        return answer;
    }
}