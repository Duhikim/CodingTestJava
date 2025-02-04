package CodingTestStudy.DoublePriorityQue;

import java.util.LinkedList;

public class Solution {

	public static void main(String[] args) {
		Solution sol = new Solution();
		String[] operations;

		operations = new String[]{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
		sol.solution(operations);
	}
	public static class MyDeque{
		public LinkedList<Integer> myDeque;

		public MyDeque(){
			myDeque = new LinkedList<>();
		}

		public void add(int n){
			if(myDeque.isEmpty()){
				myDeque.add(n);
				return;
			}

			int start=0, end=myDeque.size()-1;
			int mid;
			while(end - start > 1){
				mid = (start+end)/2;
				if(n < myDeque.get(mid)){
					end = mid;
				}
				else if(n > myDeque.get(mid)){
					start = mid;
				}
				else {
					myDeque.add(mid, n);
					return;
				}
			}
			//end - start 가 1이거나 0인 경우.
			if(end==start){
				if(n <= myDeque.get(end)){
					myDeque.add(end, n);
				}
				else {
					if (end < myDeque.size()-1) {
						myDeque.add(end + 1, n);
					}
					else{
						myDeque.add(n);
					}
				}
			}
			else{
				if(n >= myDeque.get(end)){
					if (end < myDeque.size()-1) {
						myDeque.add(end + 1, n);
					}
					else{
						myDeque.add(n);
					}
				}
				else{
					if(n>myDeque.get(start)){
						myDeque.add(end, n);
					}
					else{
						myDeque.add(start, n);
					}
				}
			}
		}
		public void delMax(){
			if(!myDeque.isEmpty()) {
				myDeque.removeLast();
			}
		}
		public void delMin(){
			if(!myDeque.isEmpty()) {
				myDeque.removeFirst();
			}
		}
		public int getLast(){
			return myDeque.getLast();
		}
		public int getFirst(){
			return myDeque.getFirst();
		}
		public boolean isEmpty(){
			return myDeque.isEmpty();
		}
	}

	public int[] solution(String[] operations) {
		int[] answer = new int[2];

		MyDeque listQue = new MyDeque();

		for(String cmd: operations){
			if(cmd.equals("D 1")){
				listQue.delMax();
			}
			else if(cmd.equals("D -1")){
				listQue.delMin();
			}
			else{
				String[] str = cmd.split(" ");
				listQue.add(Integer.parseInt(str[1]));
			}
		}
		if(!listQue.isEmpty()){
			answer[1] = listQue.getFirst();
			answer[0] = listQue.getLast();
		}

		return answer;
	}
}