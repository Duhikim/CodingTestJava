package CodingTestStudy.HotelManager;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
	public int solution(String[][] book_time) {
		// 1. 시작 시간 기준 오름차 정렬.
		// 2. room 이라는 이름의 PriorityQueue를 만들고 방이 필요할때 하나씩 늘려나갈거임. 저장된 숫자는 끝나는시간+청소시간.
		// 3. 최종적으로 room 크기 리턴하면 됨.

		Comparator<String[]> timeRangeComparator = (time1, time2) ->{
			LocalTime t1 = LocalTime.parse(time1[0]);
			LocalTime t2 = LocalTime.parse(time2[0]);
			return t1.compareTo(t2);
		};

		Arrays.sort(book_time, timeRangeComparator);

		PriorityQueue<Integer> room = new PriorityQueue<>();
		room.add(timeToMinutes(book_time[0][1]) + 10);

		for(int i=1; i<book_time.length; i++){
			int startTime = timeToMinutes(book_time[i][0]);
			int endTime = timeToMinutes(book_time[i][1]);

			if(startTime >= room.peek()) room.poll();
			room.add(endTime+10);
		}

		return room.size();
	}

	public int timeToMinutes(String HHMM){
		String[] str = HHMM.split(":");
		return Integer.parseInt(str[0])*60 + Integer.parseInt(str[1]);
	}
}