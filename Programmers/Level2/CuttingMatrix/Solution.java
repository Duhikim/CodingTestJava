package CodingTestStudy.Level2.CuttingMatrix;
import java.lang.Math;

class Solution {
    public int[] solution(int n, long left, long right) {
        int len = (int)(right-left+1);
        int[] answer = new int[len];

        for(int i=0; i<len; i++){
            int row = (int)((left+i)/n);
            int col = (int)((left+i)%n);
            answer[i] = Math.max(col+1, row+1);
        }

        return answer;
    }
}