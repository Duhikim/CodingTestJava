package CodingTestStudy.SteppingStone;

/*
<풀이>
	처음에 while문 안에서 한단계씩 빼며 체크를 해보았으나 2중 구조가 되어 효율성 테스트를 통과하지 못함.
	효율적으로 체크를 하는 방식으로 변경
	stones의 최대값과 최소값을 찾아서 중간값만큼 배열에서 빼면서 건널 수 있는지 확인.
		1. 건널 수 있는 경우 -> 최소값에 중간값을 넣고 다시 중간값을 계산한다.(중간값 증가). 다시 체크
		2. 건널 수 없는 경우 -> 최대값에 중간값을 넣고 다시 중간값을 계산한다.(중간값 감소). 다시 체크
	매 체크시마다 배열은 롤백해야 한다.
	이방법으로 nlogn으로 시간복잡도를 줄일 수 있음.

<예외>
	1. min과 max가 같은 경우. (1,1,1,1,1,1,1) or (3,3,3,3,3,3) 예상값 min.
	2. 처음부터 못건너는 경우 (1,0,0,0,0,1) k=3 예상값 0.
	3. 처음은 건널 수 있으나 min을 빼면 못건너는 경우. ( 2,1,1,1,1,2) k=3 예상값 1.

 */

public class Solution_ver2 {
	public int solution(int[] stones, int k) {
		if(stepping(stones) >= k) return 0; // 2번 예외 처리

		int answer = 0;
		int[] stonesClone = stones.clone();
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int num: stones){
			if(num > max) max = num;
			if(num < min) min = num;
		}
		int mid = (min + max)/2;

		if(min == max){
			return min; // 1번 예외 처리
		}
		for (int i = 0; i < stones.length; i++) {
			if (stonesClone[i] <= min) stonesClone[i] = 0;
			else stonesClone[i] -= min;
		}
		if(stepping(stonesClone) >= k) return min; // 3번 예외 처리
		stonesClone = stones.clone();

		while(max > min+1) {
			for (int i = 0; i < stones.length; i++) {
				if (stonesClone[i] <= mid) stonesClone[i] = 0;
				else stonesClone[i] -= mid;
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
			if (stonesClone[i] <= max) stonesClone[i] = 0;
			else stonesClone[i] -= max;
		}
		if(stepping(stonesClone) < k) answer = max;
		else answer = min;


		return answer+1;
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