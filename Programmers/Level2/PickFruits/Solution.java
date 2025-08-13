package CodingTestStudy.Level2.PickFruits;

import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {
	public int solution(int k, int[] tangerine) {
		int answer = 0;

		//Map<Integer, Integer> fruitMap = new HashMap<>();
		Map<Integer, Integer> fruitMap = new LinkedHashMap<>();
		for(int size: tangerine){
			fruitMap.put(size, fruitMap.getOrDefault(size, 0) +1);
		}

		fruitMap = fruitMap.entrySet().stream()
				.sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
				.collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);

		int sum = 0;
		for(Map.Entry<Integer, Integer> es : fruitMap.entrySet()){
			sum += es.getValue();
			answer++;
			if(sum >= k) break;
		}

		return answer;
	}
}