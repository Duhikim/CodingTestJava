package CodingTestStudy.Level2.FindBigNumberRightBehind;

import java.util.Stack;

public class Solution {
	public int[] solution(int[] numbers) {
		int[] answer = new int[numbers.length];
		answer[numbers.length-1] = -1;
		int max = numbers[numbers.length-1];
		Stack<Integer> stack = new Stack<>();
		stack.add(max);

		for(int i=numbers.length-2; i>= 0; i--){
			if(numbers[i] >= max){
				answer[i] = -1;
				max = numbers[i];
				stack.clear();
				stack.add(max);
			}
			else{
				while(true){
					if(numbers[i] < stack.peek()){
						answer[i] = stack.peek();
						stack.add(numbers[i]);
						break;
					}
					else{
						stack.pop();
					}
				}
			}
		}

		return answer;
	}
}