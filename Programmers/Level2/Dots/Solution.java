package CodingTestStudy.Level2.Dots;

public class Solution {
	public long solution(int k, int d) {
		long answer = 0;
		for(int i=0; i<=d; i += k){
			answer += (int)Math.pow((long)d*d-(long)i*i, 0.5) / k + 1;
		}
		return answer;
	}
}