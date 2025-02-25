package CodingTestStudy.PerfectCrime;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution_ver2 {
	public int solution(int[][] info, int n, int m) {
				PriorityQueue<int[]> pque
				= new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if( (double) o1[0]/o1[1] < (double) o2[0]/o2[1]) return 1;
				if( (double) o1[0]/o1[1] > (double) o2[0]/o2[1]) return -1;

				return o2[0] - o1[0];
			}
		});

		pque.addAll(Arrays.asList(info));

		int evidenceOfA = 0;
		int evidenceOfB = 0;

		while(!pque.isEmpty()){
			int[] evidence = pque.poll();
			if(evidenceOfB + evidence[1] < m){
				evidenceOfB += evidence[1];
			}
			else{
				evidenceOfA += evidence[0];
				if(evidenceOfA >= n) return -1;
			}
		}
		return evidenceOfA;
	}
}