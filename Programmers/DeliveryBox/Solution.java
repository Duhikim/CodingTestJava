package CodingTestStudy.DeliveryBox;

import java.util.ArrayDeque;

public class Solution {
	public int solution(int[] order) {
		int loadedBox = 0;
		int len = order.length;

		ArrayDeque<Integer> container = new ArrayDeque<>();
		ArrayDeque<Integer> boxStack = new ArrayDeque<>();

		for(int i=0; i<len; i++){
			container.add(i+1);
		}


		for(int target: order){
			if(!boxStack.isEmpty() && boxStack.peekLast() == target) {
				boxStack.pollLast();
				loadedBox++;
				continue;
			}
			if(!container.isEmpty() && container.peekFirst() == target){
				container.pollFirst();
				loadedBox++;
				continue;
			}

			while(!container.isEmpty() && container.peekFirst() != target){
				boxStack.add(container.pollFirst());
			}
			if(container.isEmpty()) break;
			container.pollFirst();
			loadedBox++;
		}
		return loadedBox;
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] order = {5,4,3,2,1};
		System.out.println(sol.solution(order));
	}
}