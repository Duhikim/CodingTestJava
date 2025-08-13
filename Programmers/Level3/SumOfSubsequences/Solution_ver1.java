package CodingTestStudy.Level3.SumOfSubsequences;

public class Solution_ver1 {
	public static void main(String[] args) {
		Solution_ver1 sol = new Solution_ver1();
		int[] sqc = {2, 2, 2, 2, 2};
		sol.solution(sqc, 6);
	}
	public int[] solution(int[] sequence, int k) {
		int[] answer = new int[2];
		int len = sequence.length;
		int sum = 0; // 부분 수열의 합
		int subLen = 0; // 부분 수열의 크기
		boolean find = false;

		while(++subLen <= len){
			sum = 0;
			int max = 0;

			for(int i=0; i<subLen; i++){
				sum += sequence[len-1-i];
			}
			if(sum==k) {
				find = true; break;
			}

			int smallIdx = len - 1 - subLen;
			int bigIdx = len - 1;

			while(sum > k){
				sum -= sequence[bigIdx--];
				sum += sequence[smallIdx--];

				if(sum > k && smallIdx > 0) continue;
				if(sum == k)find = true;
				break;
			}
			if(find) break;

		}
		sum = 0;
		for(int i=0; i<subLen; i++){
			sum += sequence[i];
		}
		int smallIdx = 0;
		int bigIdx = subLen;
		while(sum < k){
			sum -= sequence[smallIdx++];
			sum += sequence[bigIdx++];
		}
		answer[0] = smallIdx;
		answer[1] = bigIdx-1;

		return answer;
	}
}