package CodingTestStudy.Level2.GroupPhoto;

import java.util.HashMap;

/*
 문제 입력 제한이 널널함
 8명을 일렬로 나열 시키는 경우의 수는 8! = 대략 4만번.
 조건으로 주어지는 data의 최대 크기는 100.
 즉 최대 연산수는 4만 x 100 = 400만 밖에 안되므로 
 그냥 무식하게 모든 경우의 수를 다 확인해 봐도 된다. 
*/

class Solution {
	static char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};	
	static int[] perm_idx = new int[8];
	static boolean[] used = new boolean[8];
	static HashMap<String, Integer> friendArray = new HashMap<>();
	static int answer = 0;
	static String[] data_st;
	static int n_st;	
	
	static int solution(int n, String[] data) {
		data_st = data;
		n_st = n;
		answer=0;
    	
    	perm(0);    	
    	
    	return answer;
    }
	
	// 순열 생성 함수 (깊이 우선 탐색)
	static void perm(int idx) {
		if(idx==8) {
			friendArray.clear();
			for(int i=0; i<8; i++) {
				friendArray.put(friends[i]+"", perm_idx[i]);
			}			
			// 조건 체크. 도중에 하나라도 틀리면 break, 끝까지 가면 카운팅.
			for(int i=0; i<n_st; i++) {    		
    			if(checkCondition(friendArray, data_st[i]) == false) break;
    			if(i == n_st-1) answer++;
    		}
			
			return;
		}
		for(int i=0; i<8; i++) {
			if(used[i]) continue;
			perm_idx[idx] = i;
			used[i] = true;
			perm(idx+1);
			used[i] = false;
		}
	}
	
	
	// 조건 체크 함수. 
	static boolean checkCondition(HashMap<String, Integer> friendArray, String data) {
		
		String F1 = data.charAt(0)+"";
		String F2 = data.charAt(2)+"";
		char oper = data.charAt(3);
		int conditionNum = (int)(data.charAt(4) - '0');
		
		int gap = Math.abs(friendArray.get(F1) - friendArray.get(F2)) -1;
		
		switch(oper) {
		case '>':
			return (gap > conditionNum);
		case '<':
			return (gap < conditionNum);
		case '=':
			return (gap == conditionNum);
		}
		System.out.println("Bug check");
		return false;
	}
}