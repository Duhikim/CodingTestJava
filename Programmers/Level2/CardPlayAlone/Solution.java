package CodingTestStudy.Level2.CardPlayAlone;

import java.util.*;

public class Solution {
	public int solution(int[] cards) {

		int len = cards.length;
		boolean[] visit = new boolean[len];
		List<Integer> groupSize = new ArrayList<>();

		for(int i=0; i<len; i++){
			if(visit[i]) continue;
			int idx = i;
			visit[idx] = true;
			Set<Integer> numSet = new HashSet<>();
			numSet.add(idx+1);
			while(!numSet.contains(cards[idx])){
				numSet.add(cards[idx]);
				idx = cards[idx]-1;
				visit[idx] = true;
			}
			groupSize.add(numSet.size());
		}

		if(groupSize.size()<2) return 0;
		groupSize.sort((a, b)->b-a);
		return groupSize.get(0) * groupSize.get(1);
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] cards = {8,6,3,7,2,5,1,4};
		System.out.println(sol.solution(cards));

	}
}