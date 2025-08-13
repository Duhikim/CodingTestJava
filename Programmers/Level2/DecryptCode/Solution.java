package CodingTestStudy.Level2.DecryptCode;

import java.util.*;

public class Solution {
	public int solution(int n, int[][] q, int[] ans) {
		List<Integer> numbers = new ArrayList<>();
		for(int i=1; i<=n; i++) {
			numbers.add(i); // 1부터 n까지
		}

		for(int i=0; i < ans.length; i++){
			if(ans[i] == 5) return 1;
			if(ans[i] == 0) {
				for(int j=0; j<5; j++) {
					numbers.remove((Integer)q[i][j]);
				}
			}
		}
		if(numbers.size() == 5) return 1;

		// 후보 숫자에서 5개를 고르는 조합. 최악의 경우 30C5 = 약 14만가지
		// 위에서 고른 조합으로 q와 ans 조건이 맞는지 확인. 중간에 하나라도 틀리면 탈출
		// q의 길이는 10 이하.
		List<int[]> combinations = new ArrayList<>();
		int[] combination = new int[5];
		makeCombinations(0, 0, numbers, combination, combinations, q, ans);

		return combinations.size();
	}

	public void makeCombinations(int idx,int start, List<Integer> numbers, int[] combination, List<int[]> combinations, int[][] q, int[] ans){
		if(idx == 5){
			if(conditionCheck(combination, q, ans)) {
				System.out.println("True");
				combinations.add(combination.clone());
			}
			else System.out.println("False");
			return;
		}

		for(int i=start; i<numbers.size(); i++){
			combination[idx] = numbers.get(i);
			makeCombinations(idx+1, i+1, numbers, combination, combinations, q, ans);
		}
	}

	public boolean conditionCheck(int[] combination, int[][] q, int[] ans){
		System.out.println("확인할 조합 : [" + combination[0] + " , " +combination[1] + " , " +combination[2] + " , " +combination[3] + " , " +combination[4] + " ]");

		for(int i=0; i<q.length; i++){
			int count = 0;
			for(int j=0; j<5; j++){
				for(int k=0; k<5; k++){
					if(combination[k] == q[i][j]){
						count++;
						break;
					}
				}
			}
			if(count != ans[i]) return false;
		}
		return true;
	}
}