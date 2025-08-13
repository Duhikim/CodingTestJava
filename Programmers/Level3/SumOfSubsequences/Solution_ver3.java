package CodingTestStudy.Level3.SumOfSubsequences;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_ver3 {
	public static void main(String[] args) {
		Solution_ver3 sol = new Solution_ver3();
		int[] sqc = {1, 2, 3, 4, 5};
		sol.solution(sqc, 7);
	}
		public static int[] solution (int[] sequence, int k) {
			int[] answer = new int[2]; // 범위 기록 결과
			Queue<Integer> q = new LinkedList<>(); // Q 사용 OR sequence 의 길이 Start, End 길이로 가능.
			int sum=0, index=0, min = Integer.MAX_VALUE;
			for (int i : sequence) {
				if (sum <= k) { // 함이 k보다 작거나 같을때
					q.offer(i);
					sum += i;
				}
				while (sum > k) { // 할이 k보다 클때
					sum -= q.poll();
					index++;
				}

				if (sum == k && q.size() < min) { // 합이 k이고 길이가 min 보다 작을때(최소길이, index 우선 만족)
					answer[0] = index;
					answer [1] = index + q.size() - 1;
					min = q.size();
				}
			}
			return answer;
		}

}