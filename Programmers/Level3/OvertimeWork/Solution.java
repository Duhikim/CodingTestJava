package CodingTestStudy.Level3.OvertimeWork;

import java.util.Arrays;

public class Solution {
	public long solution(int n, int[] works) {
		long answer = 0;
		int sum = 0;

		// works의 길이가 1인 경우
		if(works.length==1){
			works[0] -= n;
			if(works[0]<0) works[0] = 0;
			return (long)works[0] * works[0];
		}
		for(int work: works){
			sum += work;
		}
		// n이 works 총합보다 크거나 같은 경우 피로도 0
		if(sum <= n) return 0;

		works = Arrays.stream(works)
				.boxed()
				.sorted((a, b) -> b-a)
				.mapToInt(Integer::intValue)
				.toArray();

		while(n>0){
			if(works[0] > works[1]){
				works[0]--; n--;
			}
			else{
				int ref=1;
				while(works[ref] == works[0]) {
					ref++;
					if(ref == works.length){
						break;
					}
				}
				works[ref-1]--; n--;
			}
		}

		for (int work : works) {
			answer += (long) work * work;
		}
		return answer;
	}
}