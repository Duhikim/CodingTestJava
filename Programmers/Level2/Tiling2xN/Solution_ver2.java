package CodingTestStudy.Level2.Tiling2xN;

class Solution_ver2 {
	public int solution(int n) {
		long prev_prev = 1;
		long prev = 1;
		long curr=0;
		long mod = 1_000_000_007;
		// F(N) = F(N-1)*1 + F(N-2)*1 끗?


		for(int i=2; i<=n; i++) {
			curr = (prev_prev + prev) % mod;
			prev_prev = prev;
			prev = curr;
		}

		return (int)curr;
	}
}