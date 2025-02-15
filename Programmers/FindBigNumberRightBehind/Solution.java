package CodingTestStudy.FindBigNumberRightBehind;

public class Solution {
	public int[] solution(int[] numbers) {
		int[] answer = new int[numbers.length];
		int max = numbers[numbers.length-1];

		for(int i=numbers.length-2; i>= 0; i--){
			if(numbers[i] >= max){
				answer[i] = -1;
				max = numbers[i];
				continue;
			}
			for(int j=i+1; j<numbers.length; j++){
				if(numbers[i]<numbers[j]){
					answer[i] = numbers[j];
					break;
				}
			}
		}
		answer[numbers.length-1] = -1;

		return answer;
	}
}