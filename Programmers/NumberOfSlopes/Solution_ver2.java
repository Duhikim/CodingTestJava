package CodingTestStudy.NumberOfSlopes;
// 시간 복잡도 : n * m * lenOfD * k = 8 * 8 * 100 * 10^9 >>>> k가 너무 커서 k처리는 따로해야할듯.
// >> 한 사이클만 dp로 해결하고 그 이후로는 시작점을 추적하여 k-1사이클 반복하면 됨.

// 결과 : 시간복잡도는 문제가 없으나 OOM (Out of memory) 발생.
// 이를 해결하기 위해 HashMap을 전부 저장하는 방식을 수정하고 cycle 어레이도 10억개나 만드는 것은 불필요할 것으로 보임.

import java.util.HashMap;
import java.util.Map;

public class Solution_ver2 {
	public int solution(int[][] grid, int[] d, int k) {
		long mod = 1_000_000_007;
		int n = grid.length;
		int m = grid[0].length;
		int lenOfD = d.length;

		long[][][][] cycleResults = new long[2][][][];
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		cycleResults[0] = new long[n][m][n*m+1]; // 마지막 자리는 판별식. 모두 0이면 0, 아니면 1.
		for(int i=0; i<n; i++)for(int j=0; j<m; j++) {
			for (int dir = 0; dir < 4; dir++) {
				int pastR = i + dr[dir];
				int pastC = j + dc[dir];
				if (pastR >= 0 && pastR < n && pastC >= 0 && pastC < m
						&& grid[pastR][pastC] + d[0] == grid[i][j]) {
					cycleResults[0][i][j][pastR * m + pastC]++;
					cycleResults[0][i][j][n*m] = 1;
				}
			}
		}

		for(int t=1; t<lenOfD; t++) {
			cycleResults[1] = new long[n][m][n * m+1];
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++) {
					for (int dir = 0; dir < 4; dir++) {
						int pastR = i + dr[dir];
						int pastC = j + dc[dir];
						if (pastR >= 0 && pastR < n && pastC >= 0 && pastC < m
								&& grid[pastR][pastC] + d[t] == grid[i][j]
								&& cycleResults[0][pastR][pastC][n * m] == 1) {
							for (int p = 0; p < n * m; p++) {
								cycleResults[1][i][j][p] += cycleResults[0][pastR][pastC][p];
								cycleResults[1][i][j][n * m] = 1;
							}
						}
					}
				}
			cycleResults[0] = cycleResults[1];
		}

		System.out.println((1) + "번 째 사이클 : ");
		long[][][] totalCount = new long[2][][];
		totalCount[0] = new long[n][m];
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++) {
				if(cycleResults[0][i][j][n*m] == 0) {
					System.out.printf("%10d    ", totalCount[0][i][j]);
					continue;
				}
				for(int p=0; p<n*m; p++){
					totalCount[0][i][j] += cycleResults[0][i][j][p];
				}
				System.out.printf("%10d    ", totalCount[0][i][j]);
			}
			System.out.println();
		}

		for(int cy=1; cy<k; cy++){
			System.out.println((cy+1) + "번 째 사이클 : ");
			totalCount[1] = new long[n][m];
			for(int i=0; i<n; i++) {
				for (int j = 0; j < m; j++) {
					if (totalCount[0][i][j] == 0 || cycleResults[0][i][j][n * m] == 0) {
						System.out.printf("%10d    ", totalCount[1][i][j]);
						continue;
					}
					for (int p = 0; p < n * m; p++) {
						int startR = p / m;
						int startC = p % m;
						totalCount[1][i][j] += (totalCount[0][startR][startC] * cycleResults[0][i][j][p]) % mod;
						totalCount[1][i][j] %= mod;
					}
					System.out.printf("%10d    ", totalCount[1][i][j]);
				}
				System.out.println("");
			}
			totalCount[0] = totalCount[1];
		}
		long answer = 0;
		for(int i=0; i<n; i++)for(int j=0; j<m; j++) {
			answer += totalCount[0][i][j];
			answer %= mod;
		}

		System.out.println(answer);
		return (int)answer;
	}

	public static void main(String[] args) {
		Solution_ver2 sol = new Solution_ver2();
		int[][] grid =
				{{0, 0, 0}, {1, 0, 0}, {0, 0, 0}, {0, 0, 1}, {1, 0, 0}};
//				{{3, 6, 11, 12}, {4, 8, 15, 10}, {2, 7, 0, 16}};
//				{{3, 4, 6, 5, 3}, {3, 5, 5, 3, 6}, {5, 6, 4, 3, 6}, {7, 4, 3, 5, 0}};
		int[] d =
				{0, 0, 1, -1, 0, 0, 1, -1};
//				{1, -2, 5};
//				{1, -2, -1, 0, 2};
		int k = 8;
		sol.solution(grid, d, k);
	}
}