package CodingTestStudy.LZWCompression;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
	public int[] solution(String msg) {

		ArrayList<Integer> ans = new ArrayList<>();

		HashMap<String, Integer> dictionary = new HashMap<>();
		for(int i=0; i< 26; i++){
			String alphabet = "" + (char)(i+'A');
			dictionary.put(alphabet, i+1);
		}

		int len = msg.length();

		for(int i=0; i<len; i++){
			String temp1 = "" + msg.charAt(i), temp2 = "";
			int idx = i;
			while(dictionary.containsKey(temp1)){
				temp2 = temp1;
				if(idx == len-1) break;
				temp1 += msg.charAt(++idx);
			}
			if(!dictionary.containsKey(temp1)) dictionary.put(temp1, dictionary.size()+1);
			ans.add(dictionary.get(temp2));
			int lenOfLastWord = temp2.length();
			while(--lenOfLastWord > 0 ) i++;
		}


		return ans.stream()
				.mapToInt(Integer::intValue)
				.toArray();
	}
}