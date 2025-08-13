package CodingTestStudy.Level2.NextBigNumber;

public class Solution {
	public int solution(int n) {

		String bin = Integer.toString(n, 2);
		int len = bin.length();
		boolean findThreshold = false;
		int thresholdIdx = -1;

		for(int i = len-1; i>=0; i--){
			if(bin.charAt(i) == '1') findThreshold = true;
			if(findThreshold && bin.charAt(i)=='0') {
				thresholdIdx = i;
				break;
			}
		}
		if(thresholdIdx == -1){
			bin = "0" + bin;
			len++;
			thresholdIdx = 0;
		}

		// threshodIdx에는 0이 있는데 이것을 1로 바꾸고
		// 그 뒤에 있는 모든 1의 개수에서 한 개 뺀 만큼 뒤(1의자리)부터 채워나감.
		// 나머지는 0으로 채움.
		StringBuilder sbBin = new StringBuilder(bin);
		int oneCount = 0;
		for(int i=thresholdIdx+1; i<len; i++){
			if(sbBin.charAt(i) == '0') break;
			oneCount++;
		}
		sbBin.setCharAt(thresholdIdx, '1');
		oneCount--;

		for(int i=len-1; i>thresholdIdx; i--){
			if(oneCount-- >0){
				sbBin.setCharAt(i, '1');
			}
			else{
				sbBin.setCharAt(i, '0');
			}
		}

		return Integer.parseInt(sbBin.toString(), 2);
	}
}