// ///////////////////////////////////////////////////////////////////////
// 		ver 3.															//
//	ver2에서 DP를 1차원 배열로 축소시킬 수 있음							//
// ///////////////////////////////////////////////////////////////////////

package CodingTestStudy.Level3.Changes;

public class Solution_ver3 {
	public int solution(int n, int[] money) {

		int[] dp = new int[n+1];
		dp[0] = 1;

		for(int i=0; i<money.length; i++){
			for(int j=money[i]; j<=n; j++){
				dp[j] += dp[j-money[i]];
			}
		}

		return dp[n];
	}

	public static void main(String[] args) {
		Solution_ver3 sol = new Solution_ver3();
		int n=5;
		int[] money = {2, 3, 5};
		System.out.println(sol.solution(n, money));
	}

}