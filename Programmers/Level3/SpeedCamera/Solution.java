package CodingTestStudy.Level3.SpeedCamera;

import java.util.Arrays;

public class Solution {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[][] routes = {{4,6},{3,4},{3,8},{5,9}, {6,8}, {10, 11}};
		sol.solution(routes);
	}
	public int solution(int[][] routes) {
		int answer = 0;
		Arrays.sort(routes, (x,y)->{return x[1]-y[1];});
		int idx = 0; // 기준 인덱스


		while(true){
			boolean repeat = false;
			answer++;
			for(int i=idx; i<routes.length; i++){
				if(routes[i][0] > routes[idx][1]){
					idx = i;
					repeat =true;
					break;
				}
			}
			if(!repeat) break;
		}

		return answer;
	}
}