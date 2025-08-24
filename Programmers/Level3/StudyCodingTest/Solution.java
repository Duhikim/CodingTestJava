package CodingTestStudy.Level3.StudyCodingTest;

/*
문제를 실제로 다 풀 필요는 없다. 알고력과 코딩력이 맥시멈만 찍으면 된다.

나의 선택지 : 알고력 공부, 코딩력 공부, 가능한 문제 풀이.

dp로 해볼까 함
dp[a][c]는 알고력이 a, 코딩력이 c가 될때까지 가능한 가장 짧은 시간이라고 하자.
dp[a][c]값을 이용하여 알고력 공부를 하든, 코딩력 공부를 하든 가능한 문제를 풀든 해서 dp의 다른 값을 구할 수 있다.

*/
import java.util.*;
import java.lang.Math;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {

        // 필요한 최대의 알고력과 코딩력 구하기
        int maxAlp = 0, maxCop = 0;
        for(int[] problem: problems){
            int a = problem[0];
            int c = problem[1];
            if(a > maxAlp) maxAlp = a;
            if(c > maxCop) maxCop = c;
        }
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        // dp[a][c]는 알고력 a, 코딩력 c에 도달하기 위한 가장 짧은 시간
        int[][] dp = new int[maxAlp+1][maxCop+1];

        for(int i=0; i<=maxAlp; i++) for(int j=0; j<=maxCop; j++){
            dp[i][j] = Integer.MAX_VALUE;
        }

        // 초기 조건 및 문제 알고력 기준 정렬
        dp[alp][cop] = 0;
        Arrays.sort(problems, (a, b) -> a[0]-b[0]);


        for(int i=alp; i<=maxAlp; i++) for(int j=cop; j<=maxCop; j++){
            if(dp[i][j] == Integer.MAX_VALUE) continue;

            // 알고력 공부
            if(i+1 <= maxAlp) dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);

            // 코딩력 공부
            if(j+1 <= maxCop) dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);

            // 문제 풀기
            for(int[] problem: problems){
                if(problem[0] > i) break;
                if(problem[1] > j) continue;
                int nA = Math.min(maxAlp, i + problem[2]);
                int nC = Math.min(maxCop, j + problem[3]);

                dp[nA][nC] = Math.min(dp[nA][nC], dp[i][j] + problem[4]);
            }
        }


        return dp[maxAlp][maxCop];
    }
}