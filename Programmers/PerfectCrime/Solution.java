package CodingTestStudy.PerfectCrime;

public class Solution {
	public int solution(int[][] info, int n, int m) {
		int[][] dp = new int [info.length+1][m];
		for(int i=0; i<= info.length; i++) for(int j=0; j<m; j++){
			dp[i][j] = Integer.MAX_VALUE;
		}

		dp[0][0] = 0;

		for(int i=1; i<= info.length; i++){
			int subA = info[i-1][0];
			int subB = info[i-1][1];

			for(int j=0; j<m; j++){
				if(dp[i-1][j] < Integer.MAX_VALUE) dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + subA);
				if(j+subB < m) dp[i][j+subB] = Math.min(dp[i][j+subB], dp[i-1][j]);
			}
		}

		int minResult = Integer.MAX_VALUE;
		for(int i=0; i<m; i++){
			minResult = Math.min(minResult, dp[info.length][i]);
		}

		return (minResult < n)? minResult: -1;
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		int[][] info = {{1, 2}, {2, 3}, {2, 1}};
		int n = 4, m = 4;
		sol.solution(info, n, m);

	}

}