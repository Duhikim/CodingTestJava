package CodingTestStudy.Level3.MultiplicationTable;

class Solution {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];

        int[] divisors = new int[e+1];
        for(int i=1; i<=e; i++){
            for(int j=i; j<=e/i; j++){
                if(i==j) divisors[i*j]++;
                else divisors[i*j] += 2;
            }
        }

        int[] best = new int[e+1];

        int max = 0;
        int maxIdx=0;
        for(int i=e; i>0; i--){
            if(divisors[i] >= max){
                max = divisors[i];
                maxIdx = i;
            }
            best[i] = maxIdx;
        }

        for(int i=0; i<starts.length; i++){
            answer[i] = best[starts[i]];
        }

        return answer;
    }
}