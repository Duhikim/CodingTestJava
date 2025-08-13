package CodingTestStudy.Level3.SumOfSubsequences;

public class Solution_ver2 {
	public static void main(String[] args) {
		Solution_ver2 sol = new Solution_ver2();
		int[] sqc = {1, 1, 1, 2, 3, 4, 5};
		sol.solution(sqc, 5);
	}
	public int[] solution(int[] sequence, int k) {
		int[] answer = new int[2];

		int len = sequence.length;
		int subLen = 1;
		long sum = 0;

		for(int i=1; i<=len; i++){
			sum += sequence[len-i];
			if(sum >= k){
				subLen = i;
				break;
			}
		}

		while(subLen <= len){
			sum = 0;
			for(int i=0; i<subLen; i++){
				sum+= sequence[i];
			}
			int smallIdx=0, bigIdx=subLen-1;
			while(sum < k){
				sum += (-sequence[smallIdx++] + sequence[++bigIdx]);
			}
			if(sum==k){
				answer[0] = smallIdx;
				answer[1] = bigIdx;
				break;
			}
			subLen++;
		}

		return answer;
	}
}