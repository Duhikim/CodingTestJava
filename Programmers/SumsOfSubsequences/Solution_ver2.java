package CodingTestStudy.SumsOfSubsequences;

import java.util.HashSet;

public class Solution_ver2 {
	public int solution(int[] elements) {

		HashSet<Integer> numSet = new HashSet<>();
		int len = elements.length;
		int[] elementsX2 = new int[2*len];
		for(int i=0; i<len; i++){
			elementsX2[i] = elementsX2[i+len] = elements[i];
		}

		int[][] table = new int[len+1][len];
		for(int i=1; i<=len; i++){
			for(int j=0; j<len; j++){
				table[i][j] = table[i-1][j] + elementsX2[j+i-1];
				numSet.add(table[i][j]);
			}
		}
		return numSet.size();
	}
}