package CodingTestStudy.NumberExpression;

public class Solution {
	public int solution(int n) {
		int answer = 0;

		for(int i=1; i<=n; i++){
			int center = 0;
			boolean hasCenter = false;
			if(n%i == 0) hasCenter = true;
			center = (hasCenter)? n/i: n/i+1;
			if(center - i/2 <= 0) break;

			if( hasCenter ) { // 중심 숫자가 있고 나누는 값이 홀수면 다른거 볼필요 없이 가능함. 짝수면 불가능
				if(i%2 == 1) answer++;
				continue;
			}

			//중심 숫자가 없는데 나누는 값이 홀수면 불가능한것같다.
			if(i%2 == 1) continue;

			int compare = 0;
			for(int j=1; j<=i/2; j++){
				compare += center-j;
				compare += center-1+j;
			}
			if(compare == n) answer++;
		}

		return answer;
	}
}
