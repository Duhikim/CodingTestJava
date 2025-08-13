package CodingTestStudy.Level2.DefenseGame;

import java.util.PriorityQueue;
import java.util.Queue;

public class Solution_ver2 {
	public int solution(int n, int k, int[] enemy) {
		Queue<Integer> pQue = new PriorityQueue<>((a, b) -> b-a);
		int N = n; // 내 병사의 수
		int K = k; // 무적권의 수

		for(int i=0; i<enemy.length; i++){
			// 내 병사의 수가 적의 수보다 많으면 무적권을 안쓰고 진행 후 적의 수를 우선순위 큐에 저장.(내림차순)
			if(N >= enemy[i]){
				N-= enemy[i];
				pQue.add(enemy[i]);
			}
			else{ // 내 병사의 수가 적의 수보다 적으면,
				// 이번 적과, 이긴 적중 가장 큰 적과 비교하여 더 센 쪽에 무적권을 사용.
				// 무적권이 없는 경우 종료.
				if(K==0) return i;
				if(!pQue.isEmpty() && pQue.peek() > enemy[i]) {
					N += pQue.poll() - enemy[i];
					pQue.add(enemy[i]);
				}
				K--;
			}
		}

		return enemy.length;
	}

	public static void main(String[] args) {
		Solution_ver2 sol = new Solution_ver2();
		int n = 7;
		int k = 3;
		int[] enemy = {4, 2, 4, 5, 3, 3, 1};

		System.out.println(sol.solution(n, k, enemy));

	}
}