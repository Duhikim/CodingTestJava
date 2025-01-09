package CodingTestStudy.DeliveryAndCollect;

public class Solution {
	public static void main(String[] args) {
		//4, 4, [25, 24, 51, 0], [51, 0, 0, 49]
		Solution sol = new Solution();
		int cap=4;
		int n = 4;
		int[] deliveries = {25, 24, 51, 0};
		int[] pickups = {51, 0, 0, 49};
		long ans = sol.solution(cap, n, deliveries, pickups);
	}

	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;
		int longest = -1;
		for(int i=n-1; i>=0; i--){
			if(deliveries[i] != 0 || pickups[i] != 0) {
				longest = i;
				break;
			}
		}
		while(longest >= 0) {
			answer += (longest+1)*2L;
			int delCrate = cap, picCrate = cap;
			for (int i = longest; i >= 0; i--) {
				if (delCrate > 0 && deliveries[i] != 0) {
					if (deliveries[i] >= delCrate) {
						deliveries[i] -= delCrate;
						delCrate = 0;
					} else {
						delCrate -= deliveries[i];
						deliveries[i] = 0;
					}
				}
				if (picCrate > 0 && pickups[i] != 0) {
					if (pickups[i] >= picCrate) {
						pickups[i] -= picCrate;
						picCrate = 0;
					} else {
						picCrate -= pickups[i];
						pickups[i] = 0;
					}
				}
				if (picCrate == 0 && delCrate == 0) {
					i = longest;
					while (i >= 0 && (pickups[i] == 0 && deliveries[i] == 0)) {
						i--;
					}
					longest = i;
					break;
				}
				if(i==0){
					longest = -1; break;
				}
			}
		}

		return answer;
	}
}