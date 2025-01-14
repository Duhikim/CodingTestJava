package CodingTestStudy.PulseSubsequence;

public class Solution {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] sequence = {-1, 9, 9, 10}; // 반례 찾음. 이것을 펄스 변환 하면 {1, 9, -9, 10} 이 되어 부분수열의 합의 최대 값은 11이 되는데 이 로직으로는 10이 나온다. 절대값으로 판단하면 안될듯.
		long ans = sol.solution(sequence);
		System.out.println(ans);
	}
	public long solution(int[] sequence) {

		// 펄스를 미리 적용.
		// 펄스가 1로 시작하든 -1로 시작하든 절대값이 큰것을 취하면 된다.
		for(int i=1; i<sequence.length; i+=2) {
			sequence[i] *= -1;
		}

		long maxPulseSubseq = sequence[0]; // 부분 수열 중 합이 가장 큰 부분 수열의 합.
		long maxPulseSubseqIncludeEnd = sequence[0]; // 맨 마지막 항이 포함된 부분 수열 중 합이 가장 큰 부분 수열의 합.
		long minPulseSubseq = sequence[0];
		long minPulseSubseqIncludeEnd = sequence[0];

		for(int i=1; i<sequence.length; i++) {
			// mPSIE에 마지막 항을 추가 했을 때, 마지막 항만 독립적으로 있는 것과 비교하여 절대값이 더 큰 것을 mPSIE에 넣는다.
			if((maxPulseSubseqIncludeEnd+sequence[i]) <= (sequence[i])) {
				maxPulseSubseqIncludeEnd = sequence[i];
			}
			else { maxPulseSubseqIncludeEnd = maxPulseSubseqIncludeEnd+sequence[i]; }

			// mPS보다 mPSIE의 절대값이 더 큰 경우, mPS를 mPSIE로 갱신한다. 아닌 경우 mPS는 그대로 유지.
			maxPulseSubseq = Math.max(maxPulseSubseqIncludeEnd, maxPulseSubseq);

			if((minPulseSubseqIncludeEnd+sequence[i]) >= (sequence[i])) {
				minPulseSubseqIncludeEnd = sequence[i];
			}
			else { minPulseSubseqIncludeEnd = minPulseSubseqIncludeEnd+sequence[i]; }

			minPulseSubseq = Math.min(minPulseSubseqIncludeEnd, minPulseSubseq);
		}

		return Math.max(maxPulseSubseq, -minPulseSubseq);
	}
}