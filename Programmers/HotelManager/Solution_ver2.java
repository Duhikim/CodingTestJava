package CodingTestStudy.HotelManager;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_ver2 {
	public int solution(String[][] book_time) {
		// 1. 시작 시간 기준 오름차 정렬.
		// 2. room 이라는 이름의 PriorityQueue를 만들고 방이 필요할때 하나씩 늘려나갈거임. 저장된 숫자는 끝나는시간+청소시간.
		// 3. 최종적으로 room 크기 리턴하면 됨.

		int[][] book_time_Min = new int[book_time.length][2];
		for(int i=0; i< book_time.length; i++){
			book_time_Min[i][0] = timeToMinutes(book_time[i][0]);
			book_time_Min[i][1] = timeToMinutes(book_time[i][1]);
		}

		Arrays.sort(book_time_Min, (a, b) -> a[0] - b[0]);

		PriorityQueue<Integer> room = new PriorityQueue<>();
		room.add(book_time_Min[0][1] + 10);

		for(int i=1; i<book_time.length; i++){
			if(book_time_Min[i][0] >= room.peek()) room.poll();
			room.add(book_time_Min[i][1]+10);
		}

		return room.size();
	}

	public int timeToMinutes(String HHMM){
		String[] str = HHMM.split(":");
		return Integer.parseInt(str[0])*60 + Integer.parseInt(str[1]);
	}
}