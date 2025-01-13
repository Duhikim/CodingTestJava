package CodingTestStudy.OvertimeWork;

import java.util.HashMap;
import java.util.Map;

public class Solution_ver2 {
	public static void main(String[] args) {
		Solution_ver2 sol = new Solution_ver2();
		int[] works = {4, 3, 3};
		sol.solution(4, works);

	}
	public long solution(int n, int[] works) {
		long answer = 0;

		int max = 0;
		int sum = 0;
		HashMap<Integer, Integer> works_map = new HashMap<>();

		for(int work: works){
			sum+= work;
			if(work>max) max = work;
			works_map.put(work, works_map.getOrDefault(work, 0) + 1);
		}
		if(sum <= n) return 0;
		while(n-- > 0){
			works_map.put(max, works_map.getOrDefault(max, 0)-1);
			works_map.put(max-1, works_map.getOrDefault(max-1, 0)+1);
			if(works_map.get(max)==0){
				works_map.remove(max);
				max -= 1;
			}
		}

		for(Map.Entry<Integer, Integer> es : works_map.entrySet()){
			answer += ((long) es.getKey() *es.getKey()) * es.getValue();
		}

		return answer;
	}
}