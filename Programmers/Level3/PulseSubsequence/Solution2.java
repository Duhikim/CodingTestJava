package CodingTestStudy.Level3.PulseSubsequence;

import java.lang.Math;
class Solution2 {
    public long solution(int[] sequence) {
        long answer = 0;

        for(int i=1; i<sequence.length; i+=2){
            sequence[i] *= -1;
        }

        long curr = 0;
        long max = 0;

        for(int i=0; i<sequence.length; i++){
            curr = Math.max(sequence[i], curr + sequence[i]);
            if(curr > max) max = curr;
        }

        curr = 0;
        long min = 0;
        for(int i=0; i<sequence.length; i++){
            curr = Math.min(sequence[i], curr + sequence[i]);
            if(curr < min) min = curr;
        }

        return Math.max(Math.abs(max), Math.abs(min));
    }
}