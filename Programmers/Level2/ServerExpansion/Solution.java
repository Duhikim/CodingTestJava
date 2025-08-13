package CodingTestStudy.Level2.ServerExpansion;

import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int solution(int[] players, int m, int k) {
		int answer = 0;

		int currentServer = 0; // 현재 서버 수
		Map<Integer, Integer> startTimeAndServers = new HashMap<>(); // (서버 개설 시작 시간, 개설된 서버 개수)

		for(int time = 0; time<24; time++){
			if(startTimeAndServers.containsKey(time-k)){
				currentServer -= startTimeAndServers.get(time-k); // k시간이 지나면 증설된 서버가 닫힘.
				startTimeAndServers.remove(time-k); // 이 줄은 있어도되고 없어도됨. 시간은 늘어나지만 메모리는 아낌
			}
			int reqServers = players[time]/m; // 필요한 서버 수
			if(reqServers > currentServer){ // 필요한 서버 수가 현재 서버수보다 많으면
				answer += reqServers - currentServer; // 그 차이만큼 서버를 증설해 준다.
				startTimeAndServers.put(time, reqServers-currentServer); // 증설 시작시간과 증설한 서버 개수 기록
				currentServer = reqServers;
			}
		}

		return answer;
	}
}