package CodingTestStudy.JadenCase;

public class Solution {
	public static void main(String[] args) {
		Solution sol = new Solution();
		sol.solution("3people unFollowed me");
	}
	public String solution(String s) {
		StringBuilder sb = new StringBuilder(s.toLowerCase());

		if(sb.charAt(0) > '9') sb.setCharAt(0, (char)(sb.charAt(0)+'A'-'a'));
		for(int i=1; i<sb.length()-1; i++){
			if(sb.charAt(i) == ' ' && sb.charAt(i+1) > '9'){
				sb.setCharAt(i+1, (char)(sb.charAt(i+1)+'A'-'a'));
			}
		}
		return sb.toString();
	}
}