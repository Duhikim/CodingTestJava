package CodingTestStudy.RepeatBinaryConversion;

public class Solution {
	public static void main(String[] args) {
		Solution sol = new Solution();

		System.out.println(sol.solution("110010101001"));
	}

	public int[] solution(String s) {
		int[] answer = new int[2];
		String[] ss = {s};

		while(!ss[0].equals("1")){
			answer[0]++;
			answer[1] += binConversion(ss);
		}
		return answer;
	}

	public int binConversion(String[] s){
		int size = 0;
		int zeros = 0;
		for(int i=0; i<s[0].length(); i++){
			if(s[0].charAt(i) == '1') size++;
			else zeros++;
		}
		s[0] = Integer.toString(size, 2);

		return zeros;
	}
}