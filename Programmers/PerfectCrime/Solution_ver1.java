package CodingTestStudy.PerfectCrime;

public class Solution_ver1 {
	public int solution(int[][] info, int n, int m) {
		int[][] dp = new int [info.length+1][m];
		for(int i=0; i<= info.length; i++){
			for(int j=0; j<m; j++){
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		dp[0][0] = 0;

		for(int i=0; i<info.length; i++){
			int subA = info[i][0];
			int subB = info[i][1];

			for(int j=0; j<m; j++){
				int temp = Math.min(dp[i+1][j], dp[i][j]+subA);
				if(temp >= 0 )dp[i+1][j] = temp;
				if(j+subB >= m) continue;
				temp = Math.min(dp[i+1][j+subB], dp[i][j]);
				if(temp >=0) dp[i+1][j+subB] = temp;
			}
		}
		int answer = -1;

		for(int i=0; i<m; i++){
			if(dp[info.length][i] < n &&
					(answer<0 || dp[info.length][i] < answer))
				answer = dp[info.length][i];
		}

		return answer;
	}

	public static void main(String[] args) {
		Solution_ver1 sol = new Solution_ver1();
		int[][] info = {{1, 2}, {2, 3}, {2, 1}};
		int n = 4, m = 4;
		sol.solution(info, n, m);

	}
}