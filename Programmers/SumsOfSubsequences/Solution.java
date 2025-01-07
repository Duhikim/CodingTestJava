package CodingTestStudy.SumsOfSubsequences;

import java.util.HashSet;

public class Solution {
	public int solution(int[] elements) {

		HashSet<Integer> numSet = new HashSet<>();
		int len = elements.length;
		int[] elementsX2 = new int[2*len];
		for(int i=0; i<len; i++){
			elementsX2[i] = elementsX2[i+len] = elements[i];
		}

		for(int i=1; i<=len; i++){ // i는 부분 수열의 길이
			for(int j=0; j<len; j++){ // j는 시작 인덱스
				int sum = 0;
				for(int k=j; k<j+i; k++){ // i부터 j+i까지 숫자를 더함
					sum += elementsX2[k];
				}
				numSet.add(sum);
			}
		}

		return numSet.size();
	}
}