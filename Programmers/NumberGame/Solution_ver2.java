package CodingTestStudy.NumberGame;

import java.util.Arrays;

public class Solution_ver2 {
	public int solution(int[] A, int[] B) {
		int answer = 0;

		Arrays.sort(A);
		Arrays.sort(B);

		int idxA = 0;
		for(int i=0; i<B.length; i++){
			if(A[idxA] < B[i]){
				answer++;
				idxA++;
			}
		}
		return answer;
	}
}