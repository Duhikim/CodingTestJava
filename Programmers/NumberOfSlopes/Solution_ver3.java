package CodingTestStudy.NumberOfSlopes;

// 시간 복잡도 : n * m * lenOfD * k = 8 * 8 * 100 * 10^9 >>>> k가 너무 커서 k처리는 따로해야할듯.
// >> 한 사이클만 dp로 해결하고 그 이후로는 시작점을 추적하여 k-1사이클 반복하면 됨.

// 결과 : 시간복잡도는 문제가 없으나 OOM (Out of memory) 발생.
// 이를 해결하기 위해 HashMap을 전부 저장하는 방식을 수정하고 cycle 어레이도 10억개나 만드는 것은 불필요할 것으로 보임.

import java.util.*;

public class Solution_ver3 {
	public int solution(int[][] grid, int[] d, int k) {
		long mod = 1_000_000_007;
		int n = grid.length;
		int m = grid[0].length;
		int lenOfD = d.length;
		int logScale = log(2, k);


		long[][] prev = new long[n*m][n*m+1]; // dp[0]은 prev, dp[1]은 curr
		long[][] curr;// = new long[n*m][n*m+1]; // 마지막 자리는 판별식. 모두 0이면 0, 아니면 1. 즉 dp[e][n*m] ==0 이면, e에 도착하는 경우가 없다는 뜻.
		int[] dr = {-m, 1, m, -1}; // 상 우 하 좌

		//System.out.println(1 + "번째 턴 : ");
		for(int e=0; e<n*m; e++) {
			for (int dir = 0; dir < 4; dir++) {
				int s = e + dr[dir]; // s : startCoordination
				if (s >= 0 && s < n*m && // 상하 범위 체크
						!(e%m == m-1 && s%m == 0) &&
						!(e%m == 0 && s%m == m-1) && // 좌우 범위 체크
						grid[s/m][s%m] + d[0] == grid[e/m][e%m]) {
					prev[e][s]++;
					prev[e][n*m] = 1;
					// System.out.println("(" + s/m + " , " + s%m + ") -> (" + e/m + " , " + e%m + ")");
				}
			}
		}

		for(int t=1; t<lenOfD; t++) {
			//System.out.println((t+1) + "번째 턴 : ");
			curr = new long[n*m][n*m+1];
			for (int e = 0; e<n*m; e++) {
				for (int dir = 0; dir < 4; dir++) {
					int s = e + dr[dir];
					if (s >= 0 && s < n*m && // 상하 범위 체크
							!(e%m == m-1 && s%m == 0) &&
							!(e%m == 0 && s%m == m-1) && // 좌우 범위 체크
							grid[s/m][s%m] + d[t] == grid[e/m][e%m] &&
							prev[s][n * m] == 1) {
						for (int p = 0; p < n * m; p++) {
							if(prev[s][p]==0) continue;
							curr[e][p] += prev[s][p];
							curr[e][n*m] = 1;
							//System.out.println("(" + p/m + " , " + p%m + ") -> (" + e/m + " , " + e%m + ") 로 가는 경우의 수 : " + curr[e][p]);
						}
					}
				}
			}
			prev = curr;
		}
		long[][] oneCycleResults = prev; // 여기까진 정상 동작
		long[][] prevCycleResults = oneCycleResults.clone();
		long[][] currCycleResults;


		long[][] totalCount = new long[3][];

		totalCount[0] = new long[n*m];
		for(int e = 0; e < n*m; e++) {
			if(oneCycleResults[e][n*m] == 0) continue;
			for(int s=0; s<n*m; s++){
				totalCount[0][e] += oneCycleResults[e][s];
				totalCount[0][e] %= mod;
			}
		}
		totalCount[1] = totalCount[0].clone();

		ArrayList<Integer> exponents = primeFactorization(k);
		for(int exp : exponents){
			int cycleIdx = 1;
			if(exp==0 && exponents.size() > 1) exp = 1;

			while (cycleIdx < exp) {
				totalCount[2] = new long[n*m];
				currCycleResults = new long[n * m][n * m + 1];
				for (int e = 0; e < n * m; e++) {
					if (prevCycleResults[e][n * m] == 0) continue;
					for (int mid = 0; mid < n * m; mid++) {
						if (prevCycleResults[mid][n*m] == 0 || prevCycleResults[e][mid] == 0) continue;
						for(int s = 0; s< n*m; s++){
							if(prevCycleResults[mid][s] == 0) continue;
							currCycleResults[e][s] += (prevCycleResults[mid][s]*prevCycleResults[e][mid])%mod;
							currCycleResults[e][s] %= mod;
							currCycleResults[e][m*n] = 1;
							totalCount[2][e] += (prevCycleResults[mid][s]*prevCycleResults[e][mid])%mod;
							totalCount[2][e] %= mod;
						}
					}
				}
				prevCycleResults = currCycleResults;
				totalCount[1] = totalCount[2];
				cycleIdx++;
			}


		}

		// totalCount[1]과 currCycleResults 만들어야함
//		int cycleIdx = 1;
//		while (cycleIdx < logScale) {
//			totalCount[2] = new long[n*m];
//			currCycleResults = new long[n * m][n * m + 1];
//			for (int e = 0; e < n * m; e++) {
//				if (prevCycleResults[e][n * m] == 0) continue;
//				for (int mid = 0; mid < n * m; mid++) {
//					if (prevCycleResults[mid][n*m] == 0 || prevCycleResults[e][mid] == 0) continue;
//					for(int s = 0; s< n*m; s++){
//						if(prevCycleResults[mid][s] == 0) continue;
//						currCycleResults[e][s] += (prevCycleResults[mid][s]*prevCycleResults[e][mid])%mod;
//						currCycleResults[e][s] %= mod;
//						currCycleResults[e][m*n] = 1;
//						totalCount[2][e] += (prevCycleResults[mid][s]*prevCycleResults[e][mid])%mod;
//						totalCount[2][e] %= mod;
//					}
//				}
//			}
//			prevCycleResults = currCycleResults;
//			totalCount[1] = totalCount[2];
//			cycleIdx++;
//		}

//		for(int rest = 0; rest<k-Math.pow(2, cycleIdx-1); rest++){
//			totalCount[2] = new long[n*m];
//
//			for(int e=0; e<n*m; e++){
//				if(prevCycleResults[e][m*n]==0) continue;
//				for(int s=0; s<n*m; s++){
//					totalCount[2][e] += (totalCount[1][s] * oneCycleResults[e][s])%mod;
//					totalCount[2][e] %= mod;
//				}
//			}
//			totalCount[1] = totalCount[2];
//		}



//
//		int cycleIndex = 1;
//		while(true){
//			int logCy = log(2, cycleIndex);
//			cycle[logCy] = new long[n*m];
//			for(int e = 0; e<n*m; e++) {
//				if(cycle[logCy-1][e] == 0 || oneCycleResults[e][n*m] == 0) continue;
//				for(int s=0; s<n*m; s++){
//					cycle[logCy][e] += (cycle[logCy-1][s] * oneCycleResults[e][s])%mod;
//					cycle[logCy][s] %= mod;
//				}
//			}
//			if(cycleIndex > k) break;
//			cycleIndex *= 2;
//		}



		long answer = 0;
		for(int e=0; e<n*m; e++) {
			answer += totalCount[1][e];
			answer %= mod;
		}
		System.out.println(answer);
		return (int)answer;
	}

	public int log(int base, int num){
		return (int)(Math.log(num) / Math.log(base)) + 1;
	}
	public ArrayList<Integer> primeFactorization(long num){
		ArrayList<Integer> result = new ArrayList<>();
		int exponent = 0;
		while(num > 0){
			if(num % 2 != 0) result.add(exponent);
			num /= 2;
			exponent++;
		}
		result.sort(Comparator.reverseOrder());
		return result;
	}

	public static void main(String[] args) {
		Solution_ver3 sol = new Solution_ver3();
		int[][] grid =
				{{0, 0, 0}, {1, 0, 0}, {0, 0, 0}, {0, 0, 1}, {1, 0, 0}};
//				{{3, 6, 11, 12}, {4, 8, 15, 10}, {2, 7, 0, 16}};
//				{{3, 4, 6, 5, 3}, {3, 5, 5, 3, 6}, {5, 6, 4, 3, 6}, {7, 4, 3, 5, 0}};
		int[] d =
				{0, 0, 1, -1, 0, 0, 1, -1};
//				{1, -2, 5};
//				{1, -2, -1, 0, 2};
		int k = 10;
		sol.solution(grid, d, k);
	}

}