package CodingTestStudy.NumberGame;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.stream.Collectors;

public class Solution_ver1 {
	public int solution(int[] A, int[] B) {
		int answer = 0;

		Arrays.sort(A);
		Arrays.sort(B);
		Queue<Integer> dequeB = Arrays.stream(B).boxed().collect(Collectors.toCollection(ArrayDeque::new));

		for(int i=0; i<A.length; i++){
			while(!dequeB.isEmpty() && A[i] >= dequeB.peek()){dequeB.poll();}
			if(dequeB.isEmpty()) break;
			dequeB.poll();
			answer++;
		}
		return answer;
	}
}