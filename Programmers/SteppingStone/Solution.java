package CodingTestStudy.SteppingStone;

import java.util.*;

public class Solution {
	public int solution(int[] stones, int k) {
		int answer = Integer.MAX_VALUE;
		Deque<Integer> deque = new ArrayDeque<>();
		Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

		for(int i=0; i<k; i++){

			deque.add(stones[i]);
			queue.add(stones[i]);
		}

		for(int i=k; i<stones.length; i++){
			answer = Math.min(answer, queue.peek());
			queue.remove(deque.pollFirst());
			deque.add(stones[i]);
			queue.add(stones[i]);
		}


		return answer;
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int k = 3;
		System.out.println(sol.solution(stones, k));
	}
}