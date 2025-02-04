package CodingTestStudy.DoublePriorityQue;

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution_ver2 {
	public int[] solution(String[] operations) {
		PriorityQueue<Integer> findMin = new PriorityQueue<>();
		PriorityQueue<Integer> findMax = new PriorityQueue<>(Collections.reverseOrder());

		for(String cmd : operations){
			if(cmd.equals("D 1")){
				if(findMax.isEmpty()) continue;
				int max = findMax.poll();
				findMin.remove(max);
			}
			else if (cmd.equals("D -1")){
				if(findMin.isEmpty()) continue;
				int min = findMin.poll();
				findMax.remove(min);
			}
			else{
				String[] str = cmd.split(" ");
				int newNum = Integer.parseInt(str[1]);
				findMin.add(newNum);
				findMax.add(newNum);
			}
		}

		int[] answer = new int[2];

		if(!findMin.isEmpty()){
			answer[0] = findMax.peek();
			answer[1] = findMin.peek();
		}

		return answer;
	}
}
