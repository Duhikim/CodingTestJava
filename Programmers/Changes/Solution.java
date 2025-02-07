package CodingTestStudy.Changes;


import java.util.Arrays;

public class Solution {
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

		int[][] dp = new int[n+1][pennyKind]; // dp[A][B]는 A원을 만들기 위해 B번째 동전(오름차순기준)까지 썼을 때의 경우의 수가 저장된다.

		// dp[*][0]은 money[0]으로 나뉘어 지면 1, 안나뉘면 0.
		for(int i=0; i<dp.length; i++){
			dp[i][0] = (i%money[0] == 0)? 1: 0;
			if(i<money[1])fillDP(dp, money, i); // money[1]보다 작은 값은 money[1]이상의 동전을 쓸일이 없음
		}

		int subChange = 0;
		for(int i=1; i<pennyKind-1; i++){
			subChange = money[i];
			while(subChange < money[i+1]){
				for(int j=1; j<=i; j++) {
					dp[subChange][j] = (dp[subChange][j - 1] + dp[subChange - money[j]][j])%mod;
				}
				fillDP(dp, money, subChange);
				subChange++;
			}
		}

		//여기까지 오면 subChange의 값은 money[pennyKind-1]이 된다. 즉 동전의 가장 큰 단위부터 n까지의 dp를 채워야 함.

		while(subChange < n){
			for(int i=1; i<pennyKind; i++) {
				dp[subChange][i] = (dp[subChange][i-1] + dp[subChange-money[i]][i])%mod; // 이제 fillDP는 필요 없음.
			}
			subChange++;
		}

		//여기까지 오면 subChange == n. dp[n][1]부터 채워나가면 됨.
		for(int i=1; i<=pennyKind-1; i++){
			dp[n][i] = (dp[n][i-1] + dp[n-money[i]][i])%mod;
		}

		return dp[n][pennyKind-1];
	}

	public void fillDP(int[][]dp, int[] money, int value){
		// money[i] > value 인 i에 대해, dp[value][i]는 dp[value][i-1]과 같다.
		int i;
		for(i=1; i<money.length; i++){
			if(money[i] > value) {
				dp[value][i] = dp[value][i-1];
			}
		}
	}
	public static void main(String[] args) {
		Solution sol = new Solution();
		int n=5;
		int[] money = {1,2,5,10};
		System.out.println(sol.solution(n, money));
	}
}