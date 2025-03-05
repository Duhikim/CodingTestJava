package CodingTestStudy.WorkProcedure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
	public class Works implements Comparable<Works> {
		String subject;
		int startTime;
		int restM;

		public Works(String[] plan){
			this.subject = plan[0];
			String[] str = plan[1].split(":");
			this.startTime = Integer.parseInt(str[1]);
			this.startTime += Integer.parseInt(str[0]) * 60;
			this.restM = Integer.parseInt(plan[2]);
		}

		public int minus(Works other){
			return this.startTime - (other.startTime + other.restM);
		}

		@Override
		public int compareTo(Works other){
			return this.startTime - other.startTime;
		}

		@Override
		public String toString(){
			return this.subject;
		}

	}

	public String[] solution(String[][] plans) {
		String[] answer = new String[plans.length];

		ArrayList<Works> workList = new ArrayList<>();
		ArrayDeque<Works> pending = new ArrayDeque<>();

		for(String[]plan: plans){
			workList.add(new Works(plan));
		}

		Collections.sort(workList);

		int answerIdx = 0;
		int arrayIdx = 0;

		while(answerIdx < plans.length){
			Works currWork = workList.get(arrayIdx++);

			if(arrayIdx == plans.length){
				answer[answerIdx++] = currWork.toString();
				while(!pending.isEmpty()){
					answer[answerIdx++] = pending.pollLast().toString();
				}
				break;
			}

			Works nextWork = workList.get(arrayIdx);
			int gap = nextWork.minus(currWork); // gap : 다음 작업의 시작 시간 - 현재 작업의 끝 시간

			if(gap == 0) { // 현재 작업이 끝나자마자 바로 다음작업 진행
				answer[answerIdx++] = currWork.toString();
			}
			else if(gap > 0){ // 현재 작업이 끝나고 다음 작업까지 시간이 있음
				answer[answerIdx++] = currWork.toString();

				while(true) {
					if(pending.isEmpty())break;
					Works lastPending = pending.peekLast();
					gap -= lastPending.restM;
					if(gap == 0){
						answer[answerIdx++] = lastPending.toString();
						pending.removeLast();
						break;
					}
					else if(gap > 0){
						answer[answerIdx++] = lastPending.toString();
						pending.removeLast();
					}
					else{
						lastPending.restM = -gap;
						break;
					}
				}
			}
			else{ // gap < 0 : 현재작업이 끝나기 전에 다음작업 시작. 현재작업은 pending
				pending.add(currWork);
				currWork.restM = -gap;
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		String[][] plans = {{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}};
		sol.solution(plans);
	}
}