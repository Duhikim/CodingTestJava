package CodingTestStudy.Level3.BestSet;

public class Solution {
    public int[] solution(int n, int s) {
        if(n>s) return new int[]{-1};

        int[] answer = new int[n];

        for(int i=0; i<n; i++){
            answer[i] = s/n;
        }

        int remain = s%n;
        if(remain == 0){
            return answer;
        }

        for(int i=0; remain>0; i++){
            answer[n-1-i]++;
            remain--;
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] input = {
                {2, 9},
                {2, 1},
                {4, 4},
                {2, 8}
        };
        int testCase = 3;
        int[] answer = sol.solution(input[testCase][0], input[testCase][1]);
        System.out.println(answer[0] + " , " + answer[1]);
    }
}