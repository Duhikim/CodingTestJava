// ///////////////////////////////////////////////////////////////////////
// 		ver 3.															//
//	ver2에서 DP를 1차원 배열로 축소시킬 수 있음							//
// ///////////////////////////////////////////////////////////////////////

package CodingTestStudy.Changes;

public class Solution_ver4 {
	public int solution(int n, int[] money) {

		int[] dp = new int[n+1];
		dp[0] = 1;
		int GCD = findGCD(money);

		for(int i=0; i<money.length; i++){
			for(int j=money[i]; j<=n; j += GCD){
				dp[j] += dp[j-money[i]];
			}
		}

		return dp[n];
	}

	public static int GCD(int a, int b){
		while(b!=0){
			int temp = b;
			b = a % b;
			a = temp;
		}
		return Math.abs(a);
	}

	public static int findGCD(int[] arr){
		int result = arr[0];
		for(int i=1; i<arr.length; i++){
			result = GCD(result, arr[i]);
			if(result == 1)return 1;
		}
		return result;
	}

	public static void main(String[] args) {
		Solution_ver4 sol = new Solution_ver4();
		int n=5;
		int[] money = {2, 3, 5};
		System.out.println(sol.solution(n, money));
	}

}