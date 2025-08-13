package CodingTestStudy.Level2.SeaSawMate;

import java.util.*;

public class Solution {
	public long solution(int[] weights) {
		long answer = 0;

		Map<Integer, Integer> weightsMap = new HashMap<>();

		for(int weight: weights){
			weightsMap.put(weight, weightsMap.getOrDefault(weight, 0) + 1);
		}

		for(Map.Entry<Integer, Integer> es: weightsMap.entrySet()){
			int sameWeight = es.getValue();
			answer += (long) sameWeight * (sameWeight-1) / 2; // nC2
			if(es.getKey() % 2 == 0 && weightsMap.containsKey(es.getKey() * 3/2)){ // 2M : 3M
				answer += (long) es.getValue() * weightsMap.get(es.getKey() * 3/2);
			}
			if(weightsMap.containsKey(es.getKey() * 2)){ // 2M : 4M
				answer += (long) es.getValue() * weightsMap.get(es.getKey() * 2);
			}
			if(es.getKey() % 3 == 0 && weightsMap.containsKey(es.getKey() * 4/3)){ // 3M : 4M
				answer += (long) es.getValue() * weightsMap.get(es.getKey() * 4/3);
			}
		}

		return answer;
	}
}