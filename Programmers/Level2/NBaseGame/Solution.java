package CodingTestStudy.Level2.NBaseGame;

public class Solution {
	public String solution(int n, int t, int m, int p) {
		// n 진수, t개의 숫자를 구해야함, m명이서 진행, p번째 차례.
		// 대충 최대 m*t만큼의 길이를 구해놓으면 될듯.

		int len = m*t;
		char[] charArr = new char[len];

		int idx=0, num=0;
		while(idx < len){
			// num을 n진수 숫자 String으로 바꿈.
			// 바꾼 숫자를 charArr에 넣어줌
			// 바꾼 숫자의 자릿수를 카운팅 해서 그만큼 idx를 올려줌.
			// num을 1 올려줌.
			// idx가 len 이상이 될때까지 반복.
			String numStr = Integer.toString(num, n);
			if(n>10) numStr = numStr.toUpperCase();
			for(int i=0; i<numStr.length(); i++){
				charArr[idx++] = numStr.charAt(i);
				if(idx >= len) break;
			}
			num++;
		}
		StringBuilder answer = new StringBuilder();

		for(int i=p-1; i<charArr.length; i += m){
			answer.append(charArr[i]);
		}

		return answer.toString();
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		sol.solution(2,4,2,1);
	}
}