package CodingTestStudy.WayToSchool;

public class Solution {
	public int solution(int m, int n, int[][] puddles) {
		int mod = 1_000_000_007;

		// 2차원 배열 ways를 만듦. ways[x][y]는 (x,y)좌표까지 도달할 수 있는 경우의 수를 뜻함.
		int[][] ways = new int[n+1][m+1];

		// 물웅덩이 좌표를 -1로 만들어 줌.
		for(int i=0; i<puddles.length; i++){
			ways[puddles[i][1]][puddles[i][0]] = -1;
			System.out.println("(" + puddles[i][0] + " , " + puddles[i][1] + ") 에 물 웅덩이");
		}

		// 1열과 1행에 물웅덩이를 만날 때 까지 1을 넣음.
		for(int i=1; i<=m; i++){
			if(ways[1][i] == -1) break;
			ways[1][i] = 1;
			System.out.println("(" + i + " , " + 1 + ") 까지의 경우의 수 : " + ways[1][i]);
		}
		for(int i=1; i<=n; i++){
			if(ways[i][1] == -1) break;
			ways[i][1] = 1;
			System.out.println("(" + 1 + " , " + i + ") 까지의 경우의 수 : " + ways[i][1]);
		}

		// (2,2)부터 시작함. 바로 위의 숫자 + 바로 왼쪽의 숫자를 더하면 될듯. -1인 경우 0으로 처리.
		int upNum = 0, leftNum = 0;
		for(int i=2; i<=m; i++){
			for(int j=2; j<=n; j++){
				if(ways[j][i] == -1) continue;
				upNum = (ways[j-1][i]==-1)? 0 : ways[j-1][i];
				leftNum = (ways[j][i-1]==-1)? 0 : ways[j][i-1];
				ways[j][i] = (upNum + leftNum)%mod;
				System.out.println("(" + i + " , " + j + ") 까지의 경우의 수 : " + ways[j][i]);
			}
		}

		return ways[n][m];
	}
}