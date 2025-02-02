package CodingTestStudy.WordChain;

import java.util.HashSet;

public class Solution {
	public int[] solution(int n, String[] words) {
		int[] answer = new int[]{0,0};

		HashSet<String> wordSet = new HashSet<>();
		int faultIndex = -1;
		char lastChar = '0';

		for(int i=0; i<words.length; i++){
			if(wordSet.contains(words[i])
				|| ((words[i].charAt(0) != lastChar) && (lastChar != '0'))
			){
				faultIndex = i;
				break;
			}
			wordSet.add(words[i]);
			lastChar = words[i].charAt(words[i].length()-1);
		}
		if(faultIndex != -1){
			answer[0] = (faultIndex % n) +1;
			answer[1] = (faultIndex / n) +1;
		}

		return answer;
	}
}