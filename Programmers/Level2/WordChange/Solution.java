package CodingTestStudy.Level2.WordChange;

public class Solution {
	public int solution(String begin, String target, String[] words) {
		int[] answer = new int[1];
		answer[0] = Integer.MAX_VALUE;
		boolean[] visit = new boolean[words.length];

		Dfs(begin, target, words, visit, 0, answer);

		return (answer[0] == Integer.MAX_VALUE)? 0: answer[0];
	}

	public void Dfs(String curr, String target, String[] words, boolean[] visit, int n, int[] answer){
		if(curr.equals(target)){
			answer[0] = Math.min(n, answer[0]);
			return;
		}
		if(n >= answer[0]) return;

		for(int i=0; i< words.length; i++){
			if(!visit[i] && canSwap(curr, words[i])){
				visit[i] = true;
				Dfs(words[i], target, words, visit, n+1, answer);
				visit[i] = false;
			}
		}
	}
	public boolean canSwap(String word1, String word2){
		int count = 0;
		for(int i=0; i< word1.length(); i++){
			if(word1.charAt(i) != word2.charAt(i)){
				count++;
			}
			if(count>1) return false;
		}
		return true;
	}
}