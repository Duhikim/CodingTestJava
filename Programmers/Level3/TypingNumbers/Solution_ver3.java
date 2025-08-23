package CodingTestStudy.Level3.TypingNumbers;

import java.lang.Math;
class Solution_ver3 {
    int[][] costs = {
            {1, 7, 6, 7, 5, 4, 5, 3, 2, 3},
            {7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
            {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
            {7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
            {5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
            {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
            {5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
            {3, 4, 5, 6, 2, 3, 5, 1, 2, 4},
            {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
            {3, 6, 5, 4, 5, 3, 2, 4, 2, 1}
    };
    public int solution(String numbers) {
        int answer = Integer.MAX_VALUE;
        int n = numbers.length();

        int[][][] dp = new int[n][10][10]; // 턴, 왼손, 오른손
        int t = numbers.charAt(0) - '0';
        dp[0][4][t] = costs[6][t];
        dp[0][t][6] = costs[4][t];

        for(int i=1; i<n; i++){
            t = numbers.charAt(i) - '0';
            for(int l=0; l<10; l++) for(int r=0; r<10; r++){
                if(dp[i-1][l][r] == 0) continue;
                int oValue = dp[i][l][t];
                int nValue = dp[i-1][l][r] + costs[r][t];
                if(l!=t && (oValue==0 || oValue > nValue)) dp[i][l][t] = nValue;
                oValue = dp[i][t][r];
                nValue = dp[i-1][l][r] + costs[l][t];
                if(r!=t && (oValue==0 || oValue > nValue)) dp[i][t][r] = nValue;
            }
        }

        for(int l=0; l<10; l++) for(int r=0; r<10; r++){
            if(dp[n-1][l][r] == 0 ) continue;
            answer = Math.min(answer, dp[n-1][l][r]);
        }

        return answer;
    }
}