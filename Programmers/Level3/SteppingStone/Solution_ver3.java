package CodingTestStudy.Level3.SteppingStone;

/*
<풀이>
	처음에 while문 안에서 한단계씩 빼며 체크를 해보았으나 2중 구조가 되어 효율성 테스트를 통과하지 못함.
	효율적으로 체크를 하는 방식으로 변경
	stones의 최대값과 최소값을 찾아서 중간값만큼 배열에서 빼면서 건널 수 있는지 확인.
		1. 건널 수 있는 경우 -> 최소값에 중간값을 넣고 다시 중간값을 계산한다.(중간값 증가). 다시 체크
		2. 건널 수 없는 경우 -> 최대값에 중간값을 넣고 다시 중간값을 계산한다.(중간값 감소). 다시 체크
	매 체크시마다 배열은 롤백해야 한다.
	이방법으로 nlogn으로 시간복잡도를 줄일 수 있음.

 */


public class Solution_ver3 {
	public int solution(int[] stones, int k) {

		int[] stonesClone = stones.clone();
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int num: stones){
			if(num > max) max = num;
			if(num < min) min = num;
		}
		int mid = (min + max)/2;

		while(max > min+1) {
			for (int i = 0; i < stones.length; i++) {
				if (stonesClone[i] <= (mid-1)) stonesClone[i] = 0;
				else stonesClone[i] -= (mid-1);
			}
			if (stepping(stonesClone) < k) { // OK
				stonesClone = stones.clone();
				min = mid;
				mid = (min + max)/2;
			} else { // Not OK.
				stonesClone = stones.clone();
				max = mid;
				mid = (min + max)/2;
			}
		}

		for (int i = 0; i < stones.length; i++) {
			if (stonesClone[i] <= (max-1)) stonesClone[i] = 0;
			else stonesClone[i] -= (max-1);
		}
		if(stepping(stonesClone) < k) return max;
		else return min;
	}

	public int stepping(int[] stones){
		int maxSkip = 0;
		int skip = 0;

		for(int i=0; i<stones.length; i++){
			if(stones[i] == 0) skip++;
			else {
				maxSkip = Math.max(maxSkip, skip);
				skip = 0;
			}
		}
		maxSkip = Math.max(maxSkip, skip);

		return maxSkip;
	}
}