package CodingTestStudy.RepeatBinaryConversion;

public class Solution_ver2 {

	public int[] solution(String s) {
		int[] answer = new int[2]; // answer[0] : 변환 횟수, answer[1] : 제거된 0의 개수

		while(!s.equals("1")){
			s = binConversion(s, answer);
		}
		return answer;
	}

	public String binConversion(String s, int[] answer){
		int size = 0;
		int zeros = 0;
		for(int i=0; i<s.length(); i++){
			if(s.charAt(i) == '1') size++;
			else zeros++;
		}

		answer[0]++;
		answer[1] += zeros;
		return Integer.toString(size, 2);
	}
}