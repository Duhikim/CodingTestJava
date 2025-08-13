// ///////////////////////////////////////////////////////////////////////
// 		ver 2.															//
//	ver1에서 필요한 DP만 구해서 답을 도출하는 방향으로 바꿀것임.		//
// ///////////////////////////////////////////////////////////////////////

package CodingTestStudy.Level3.Changes;
import java.util.Arrays;

public class Solution_ver2 {
	public int solution(int n, int[] money) {
		int mod = 1_000_000_007;

		//주어진 배열을 오름차순 정렬한 후, n보다 큰 동전은 삭제할것임. (필요가 없음)
		Arrays.sort(money);
		for(int i=money.length-1; i>=0; i--){
			if(money[i] <= n){
				money = Arrays.copyOfRange(money,0, i+1);
				break;
			}
		}
		int pennyKind = money.length; // 동전의 종류

		// int[][] dp = new int[n+1][pennyKind]; // dp[A][B]는 A원을 만들기 위해 B번째 동전(오름차순기준)까지 썼을 때의 경우의 수가 저장된다.
		int[][] dp = new int[pennyKind][n+1]; // 성능을 위해 ver1에서 행과 열을 바꿨다. 행 우선방식이 유의미하게 빠를 것을 판단된다.


		// dp[0][*]은 money[0]으로 나뉘어 지면 1, 안나뉘면 0.
		for(int i=0; i<=n; i++){
			dp[0][i] = (i % money[0] == 0)? 1 : 0;
		}

		// dp[*][0]는 모두 1.
		for(int row=1; row<pennyKind; row++){
			dp[row][0] = 1;
		}

		for(int row=1; row<pennyKind; row++) {
			for (int subChange = 1; subChange <= n-money[row]; subChange++) {
				dp[row][subChange] = (subChange-money[row]>=0)?
						(dp[row-1][subChange] + dp[row][subChange-money[row]])%mod:
						dp[row-1][subChange];
			}
		}

		for(int row = 1; row < pennyKind; row++){
			dp[row][n] = (dp[row-1][n] + dp[row][n-money[row]])%mod;
		}

		return dp[pennyKind-1][n];
	}


	public static void main(String[] args) {
		Solution_ver2 sol = new Solution_ver2();
		int n=10;
		int[] money = {1,2,5,10};
		System.out.println(sol.solution(n, money));
	}
}