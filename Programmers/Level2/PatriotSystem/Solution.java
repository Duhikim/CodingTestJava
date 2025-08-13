package CodingTestStudy.Level2.PatriotSystem;

import java.util.Arrays;

public class Solution {
	public int solution(int[][] targets) {
		int answer = 1;

		Arrays.sort(targets, (a, b) -> Integer.compare(a[0], b[0]));
		int refIdx = 0;

		for(int i=1; i<targets.length; i++){
			if(targets[i][1] <= targets[refIdx][1]) refIdx = i; // 1. 레퍼런스가 비교미사일을 포함함. 레퍼런스 변경
			else if(targets[i][0] < targets[refIdx][1]) continue; // 2. 비교 미사일이 레퍼런스 끝자락에 걸쳐있음. 끝에서 한번에 요격 가능.
			else { // 3. 비교 미사일과 레퍼런스가 아예 떨어져있어서 한번에 요격 불가능. 카운트 올려주고 레퍼런스 변경.
				answer++;
				refIdx = i;
			}
		}


		return answer;
	}
}