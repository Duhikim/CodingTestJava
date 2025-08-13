package CodingTestStudy.Level2.PickFruits;

import java.util.*;

public class Solution_ver1 {
	public int solution(int k, int[] tangerine) {
		int answer = 0;

		Map<Integer, Integer> fruitMap = new HashMap<>();
		for(int size: tangerine){
			fruitMap.put(size, fruitMap.getOrDefault(size, 0) +1);
		}
		Queue<Integer> priorityQue = new PriorityQueue<>(Comparator.reverseOrder());
		for(Map.Entry<Integer, Integer> es : fruitMap.entrySet()){
			priorityQue.add(es.getValue());
		}
		int sum = 0;
		do{
			answer++;
		}while((sum += priorityQue.poll()) < k);

		return answer;
	}
}