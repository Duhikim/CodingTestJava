package CodingTestStudy.DefenseGame;

import java.util.PriorityQueue;
import java.util.Queue;

public class Solution_ver3 {
	public int solution(int n, int k, int[] enemy) {
		Queue<Integer> pQue = new PriorityQueue<>();
		int N = n; // 내 병사의 수
		int K = k; // 무적권의 수

		for(int i=0; i<enemy.length; i++){
			if(K > 0){
				pQue.add(enemy[i]);
				K--;
			}
			else{
				int mostWeakEnemy;
				if(pQue.isEmpty() || enemy[i] < pQue.peek()){
					mostWeakEnemy = enemy[i];
				}
				else{
					mostWeakEnemy = pQue.poll();
					pQue.add(enemy[i]);
				}
				N -= mostWeakEnemy;
				if(N<0) return i;
			}
		}

		return enemy.length;
	}

	public static void main(String[] args) {
		Solution_ver3 sol = new Solution_ver3();
		int n = 7;
		int k = 3;
		int[] enemy = {4, 2, 4, 5, 3, 3, 1};

		System.out.println(sol.solution(n, k, enemy));

	}
}