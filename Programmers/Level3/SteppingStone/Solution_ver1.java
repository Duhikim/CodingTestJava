package CodingTestStudy.Level3.SteppingStone;

public class Solution_ver1 {
	public int solution(int[] stones, int k) {
		int min = Integer.MAX_VALUE;
		for(int num : stones){
			if(num < min) min = num;
		}
		int answer = min;

		for(int i=0; i<stones.length; i++){
			stones[i] -= min;
		}
		while(stepping(stones) < k){
			answer++;
		}

		return answer;
	}

	public int stepping(int[] stones){
		int maxSkip = 0;
		int skip = 0;

		for(int i=0; i<stones.length; i++){
			if(stones[i] == 0) skip++;
			else {
				maxSkip = Math.max(maxSkip, skip);
				skip = 0;
				stones[i] -= 1;
			}
		}
		maxSkip = Math.max(maxSkip, skip);

		return maxSkip;
	}
}