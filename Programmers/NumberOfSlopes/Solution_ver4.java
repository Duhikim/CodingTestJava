package CodingTestStudy.NumberOfSlopes;

import java.util.*;

public class Solution_ver4 {
	int n, m, lenOfD;
	long mod = 1_000_000_007;
	public int solution(int[][] grid, int[] d, int k) {

		n = grid.length;
		m = grid[0].length;
		lenOfD = d.length;
		int logScale = log(2, k);

		// 한바퀴를 돌았을 때 시작점-끝점 경로 추적. ( 배열[끝점][시작점] = 경우의수 )
		long[][] oneCycleRouteTracer = makeOneCycleRouteTracer(grid, d);



		// nCycleRouteTracer[n]은 2^n번 돌릴때의 시작점-끝점 경우의수 체크.
		long[][][] nCycleRouteTracer = new long[logScale][][];
		nCycleRouteTracer[0] = oneCycleRouteTracer.clone();

		int idx = 1;
		while(idx < logScale) {
			nCycleRouteTracer[idx] = new long[n * m][n * m + 1];
			for (int e = 0; e < n * m; e++) {
				if (nCycleRouteTracer[idx-1][e][n * m] == 0) continue;
				for (int mid = 0; mid < n * m; mid++) {
					if (nCycleRouteTracer[idx-1][mid][n * m] == 0) continue;
					for (int s = 0; s < n * m; s++) {
						if(nCycleRouteTracer[idx-1][e][mid] == 0) continue;
						nCycleRouteTracer[idx][e][s] += (nCycleRouteTracer[idx-1][e][mid] * nCycleRouteTracer[idx-1][mid][s]) % mod;
						nCycleRouteTracer[idx][e][s] %= mod;
						nCycleRouteTracer[idx][e][n * m] = 1;
					}
				}
			}
			idx++;
		}

		long[] baseFeild = new long[n*m];
		for(int i=0; i<n*m; i++)baseFeild[i] = 1;

		ArrayList<Integer> exponents = primeFactorization(k);

		long[] renewFeild;

		for(int exp : exponents){
			renewFeild = new long[n*m];
			for(int e=0; e<n*m; e++) {
				if(nCycleRouteTracer[exp][e][n*m]==0) continue;
				for (int s = 0; s < n * m; s++) {
					renewFeild[e] += (nCycleRouteTracer[exp][e][s] * baseFeild[s])%mod;
					renewFeild[e] %= mod;
				}
			}
			baseFeild = renewFeild;
		}

		long answer = 0;
		for(int e=0; e<n*m; e++){
			answer+= baseFeild[e];
			answer%=mod;
		}
		System.out.println(answer);
		return (int)answer;

	}



	public int log(int base, int num){
		return (int)(Math.log(num) / Math.log(base)) + 1;
	}

	public long[][] makeOneCycleRouteTracer(int[][] grid, int[]d){
		long[][] prev = new long[n*m][n*m+1]; // 차원을 줄이기 위해 (a, b)를 a*m + b로 한 숫자로 표현. 역연산도 가능함.
		long[][] curr;// = new long[n*m][n*m+1]; // 마지막 자리는 판별식. 모두 0이면 0, 아니면 1. 즉 dp[e][n*m] ==0 이면, e에 도착하는 경우가 없다는 뜻.
		int[] dr = {-m, 1, m, -1}; // 상 우 하 좌

		//for(int e=0; e<n*m; e++)for(int s=0; s<n*m+1; s++) prev[e][s] = 1;

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
		return prev;
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
		Solution_ver4 sol = new Solution_ver4();
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


/*******************************************************

 long[][] prevCycleResults = oneCycleResults.clone();
 long[][] currCycleResults;


 // Todo
 // 2사이클, 4사이클, 8사이클, .... 돌릴때 루트를 저장하는 3차원 long 배열을 만들것.
 // 이것은 초기 필드값이 모두 1인 기본값으로, 나중에 base를 바꿔가며 꺼내쓸 수 있다.
 /**


 long[][][] cycleResultsForLogIdx = new long[logScale][][];
 cycleResultsForLogIdx[0] = oneCycleResults.clone();

 int cycleIdx = 1;
 while (cycleIdx < logScale) {
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
 }
 }
 }
 prevCycleResults = currCycleResults;
 cycleIdx++;
 }

 *********************************


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

 *******************************************************/