package CodingTestStudy.NumberOfSlopes;

// 시간 복잡도 : n * m * lenOfD * k = 8 * 8 * 100 * 10^9 >>>> k가 너무 커서 k처리는 따로해야할듯.

import java.util.*;

public class Solution {
	public int solution(int[][] grid, int[] d, int k) {
		long mod = 1_000_000_007;
		int n = grid.length;
		int m = grid[0].length;
		int lenOfD = d.length;

		// traces[t][r][c] 는 t번째 턴에 (r,c)로 올 수 있는 경우의 수. 안에는 (시작점, 도착경우의수) 데이터가 담겨있다.
		// 한바퀴 분량의 trace를 완성하고 나면 그다음부터는 중간과정은 관심 없으므로 길이는 lenOfD + (k-1)로 한다.
		HashMap<Integer, Long>[][][] traces = new HashMap[lenOfD][n][m];
		long[][][] cycle = new long[k][n][m];

		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		for(int i=0; i<n; i++)for(int j=0; j<m; j++){
			for(int dir=0; dir<4; dir++) {
				int pastR = i+dr[dir];
				int pastC = j+dc[dir];
				if (pastR >= 0 && pastR < n && pastC >= 0 && pastC < m
						&& grid[pastR][pastC] + d[0] == grid[i][j]) {
					if(traces[0][i][j]==null) traces[0][i][j] = new HashMap<>();
					traces[0][i][j].put(pastR*m + pastC, 1L);
				}
			}
		}

		// 4중for문이지만, 실질적으로 시간복잡도는 8*8*100*4밖에 안된다.
		for(int turn=1; turn<lenOfD; turn++){
			for(int i=0; i<n; i++)for(int j=0; j<m; j++){
				for(int dir=0; dir<4; dir++){
					int pastR = i+dr[dir];
					int pastC = j+dc[dir];
					if (pastR >= 0 && pastR < n && pastC >= 0 && pastC < m
							&& grid[pastR][pastC] + d[turn%lenOfD] == grid[i][j]
							&& traces[turn-1][pastR][pastC] != null){
						if(traces[turn][i][j]==null) traces[turn][i][j] = new HashMap<>();
						for(Map.Entry<Integer, Long> es : traces[turn-1][pastR][pastC].entrySet()) {
							traces[turn][i][j].put(es.getKey(), (traces[turn][i][j].getOrDefault(es.getKey(), 0L)+es.getValue())%mod);
						}
					}
				}
			}
		}
		for(int i=0; i<n; i++)for(int j=0; j<m; j++){
			if(traces[lenOfD-1][i][j] != null) {
				for(Map.Entry<Integer, Long> es : traces[lenOfD-1][i][j].entrySet())
					cycle[0][i][j] += es.getValue();
			}
		}

		for(int K=1; K<k; K++){
			for(int i=0; i<n; i++)for(int j=0; j<m; j++){
				if(cycle[K-1][i][j] == 0 || traces[lenOfD-1][i][j] == null) continue;
				for(Map.Entry<Integer, Long> es : traces[lenOfD-1][i][j].entrySet()){
					int startCoord = es.getKey();
					int startR = startCoord/m;
					int startC = startCoord%m;
					cycle[K][i][j] += (cycle[K-1][startR][startC] * es.getValue())%mod;
					cycle[K][i][j] %= mod;
				}
			}
		}

		long answer = 0;
		for(int i=0; i<n; i++)for(int j=0; j<m; j++){
			answer += cycle[k-1][i][j];
			answer %= mod;
		}
		System.out.println(answer);
		return (int)answer;
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		int[][] grid =
				{{0, 0, 0}, {1, 0, 0}, {0, 0, 0}, {0, 0, 1}, {1, 0, 0}};
				//{{3, 6, 11, 12}, {4, 8, 15, 10}, {2, 7, 0, 16}};
				//{{3, 4, 6, 5, 3}, {3, 5, 5, 3, 6}, {5, 6, 4, 3, 6}, {7, 4, 3, 5, 0}};
		int[] d =
				{0, 0, 1, -1, 0, 0, 1, -1};
				//{1, -2, 5};
				//{1, -2, -1, 0, 2};
		int k = 10;
		sol.solution(grid, d, k);
	}

}