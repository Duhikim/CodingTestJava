package CodingTestStudy.Level2.DeliveryAndCollect;

public class Solution_ver2 {
	public static void main(String[] args) {
		Solution_ver2 sol = new Solution_ver2();
		int cap=2;
		int n = 7;
		int[] deliveries = {1, 0, 2, 0, 1, 0, 2};
		int[] pickups = {0, 2, 0, 1, 0, 2, 0};
		sol.solution(cap, n, deliveries, pickups);
	}

	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;
		int longestDel = n, longestPic = n;
		while(deliveries[longestDel-1] == 0){
			longestDel--;
			if(longestDel==0) break;
		}
		while(pickups[longestPic-1] == 0){
			longestPic--;
			if(longestPic==0) break;
		}

		while(longestPic > 0 || longestDel > 0){
			answer += (Math.max(longestDel, longestPic))* 2L;
			int crate = cap;
			for(int i=longestDel-1; i>=0; i--){
				if(deliveries[i] != 0){
					if(deliveries[i] >= crate) {
						deliveries[i] -= crate;
						while(i>=0 && deliveries[i]==0){i--;}
						longestDel = i+1;
						break;
					}
					else{
						crate -= deliveries[i];
						deliveries[i] = 0;
					}
				}
				if(i==0) longestDel = 0;
			}
			crate = cap;
			for(int i=longestPic-1; i>=0; i--){
				if(pickups[i] != 0){
					if(pickups[i] >= crate) {
						pickups[i] -= crate;
						while(i>=0 && pickups[i]==0){i--;}
						longestPic = i+1;
						break;
					}
					else{
						crate -= pickups[i];
						pickups[i] = 0;
					}
				}
				if(i==0) longestPic = 0;
			}
		}

		return answer;
	}
}