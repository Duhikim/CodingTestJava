package CodingTestStudy.Level3.CountDown;

class Solution {
    public int[] solution(int target) {
        int[] single = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,50}; // 불도 싱글과 같이 처리
        int[] doubleTriple = {21,22,24,26,27,28,30,32,33,34,36,38,39,40,42,45,48,51,54,57,60};

        int[][] dp = new int[target+60][2];

        for(int s: single){
            dp[s][0] = 1; dp[s][1]=1;
        }
        for(int d: doubleTriple){
            dp[d][0] = 1; dp[d][1] = 0;
        }

        for(int i=21; i<=target; i++){
            for(int j=0; j<single.length; j++){
                int prevIdx = i-single[j];
                if(prevIdx < 1) break;
                if(dp[i][0] == 0
                        || dp[i][0] > dp[prevIdx][0]+1
                        || (dp[i][0] == dp[prevIdx][0]+1 && dp[i][1] < dp[prevIdx][1]+1)){
                    dp[i][0] = dp[prevIdx][0]+1;
                    dp[i][1] = dp[prevIdx][1]+1;
                }
            }
            for(int j=0; j<doubleTriple.length; j++){
                int prevIdx = i-doubleTriple[j];
                if(prevIdx < 1) break;
                if(dp[i][0] == 0
                        || dp[i][0] > dp[prevIdx][0]+1
                        || (dp[i][0] == dp[prevIdx][0]+1 && dp[i][1] < dp[prevIdx][1]+1)){
                    dp[i][0] = dp[prevIdx][0]+1;
                    dp[i][1] = dp[prevIdx][1];
                }
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] answer = sol.solution(60);
        System.out.println(answer[0] + " , " + answer[1]);


    }
}