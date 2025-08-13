package CodingTestStudy.Level2.Tiling2xN;

class Solution {
	public int solution(int n) {
		long dp[] = new long[n+1];
		long mod = 1_000_000_007;
		// F(N) = F(N-1)*1 + F(N-2)*1 끗?

		dp[0] = 1; dp[1] = 1;
		for(int i=2; i<=n; i++){
			dp[i] = (dp[i-1] + dp[i-2])%mod;
		}

		return (int)dp[n];
	}
}